package com.zyf.redenvelopesystem.async.consumer;

import com.zyf.redenvelopesystem.common.database.dao.StoreEnvelopeMapper;
import com.zyf.redenvelopesystem.common.database.dao.StoreEnvelopeRecordMapper;
import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelope;
import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelopeRecord;
import com.zyf.redenvelopesystem.common.redis.util.RedisUtil;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeRecordVo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
@RabbitListener(queues= "GetEnvelopeQueue")
public class GetEnvelopeQueueConsumer {
    final
    RabbitTemplate rabbitTemplate;
    private final RedissonClient redissonClient;
    final
    RedisUtil redisUtil;
    final
    StoreEnvelopeRecordMapper storeEnvelopeRecordMapper;
    final
    StoreEnvelopeMapper storeEnvelopeMapper;

    public GetEnvelopeQueueConsumer(StoreEnvelopeMapper storeEnvelopeMapper, RedisUtil redisUtil, StoreEnvelopeRecordMapper storeEnvelopeRecordMapper, RedissonClient redissonClient, RabbitTemplate rabbitTemplate) {
        this.storeEnvelopeMapper = storeEnvelopeMapper;
        this.redisUtil = redisUtil;
        this.storeEnvelopeRecordMapper = storeEnvelopeRecordMapper;
        this.redissonClient = redissonClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 悲观锁实现
     */
    @RabbitHandler
    public void process(Map<String,String> param) {
        //解析消息队列传过来的参数
        String activityId=param.get("activityId");
        String memberId=param.get("memberId");
        String recordId=param.get("recordId");
        String redisRecordId="ASYNC:FIGHT_RECORD:"+recordId;
        //抢红包记录 根据抢的结果持久化到数据库
        StoreEnvelopeRecord storeEnvelopeRecord =new StoreEnvelopeRecord();
        storeEnvelopeRecord.setId(recordId);
        storeEnvelopeRecord.setActivityId(activityId);
        storeEnvelopeRecord.setMemberId(memberId);
        //抢红包记录的redis缓存
        StoreEnvelopeRecordVo storeEnvelopeRecordVo = (StoreEnvelopeRecordVo) redisUtil.get(redisRecordId);
        if (storeEnvelopeRecordVo==null){
            return;
        }
        storeEnvelopeRecordVo.setStatus(2);
        //获取小红包列表
        ArrayList<StoreEnvelope> storeEnvelopes=new ArrayList<>();
        for (StoreEnvelope storeEnvelope:storeEnvelopeMapper.selectByActivityId(activityId)){
            if (storeEnvelope.getRecordId()==null){
                storeEnvelopes.add(storeEnvelope);
            }
        }
        //用户没抢到
        if (storeEnvelopes.size()==0){
            storeEnvelopeRecord.setStatus(2);
            //将抢红包记录持久化
            storeEnvelopeRecordMapper.insert(storeEnvelopeRecord);
            //缓存更新
            redisUtil.set(redisRecordId, storeEnvelopeRecordVo);
            return;
        }
        //随机出抢占哪个小红包
        long rand=System.currentTimeMillis();
        long index=rand&(storeEnvelopes.size()-1);
        String envelopeId=storeEnvelopes.get((int) index).getId();
        //获取分布式锁
        String lockKey="ASYNC:FIGHT_ENVELOPE_LOCK:"+envelopeId;
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        try{
            boolean success=true;
            StoreEnvelope storeEnvelope=storeEnvelopeMapper.selectByPrimaryKey(envelopeId);
            //查询红包是否没被领取过
            if (storeEnvelope.getRecordId()!=null){
                //重新排队
                rabbitTemplate.convertAndSend("GetEnvelopeQueue",param);
                return;
            }
            //查询用户是否已经领取过
            int count=storeEnvelopeRecordMapper.selectSuccessCountByMemberIdAndActivityId(memberId,activityId);
            //用户已经领取过了
            if (count>0){
                storeEnvelopeRecord.setStatus(3);
                success=false;
            }
            if (success){
                //抢占该红包
                storeEnvelope.setRecordId(recordId);
                //抢红包状态设为成功
                storeEnvelopeRecord.setStatus(1);
                storeEnvelopeRecordVo.setStatus(1);
                storeEnvelopeRecordVo.setMoney(storeEnvelope.getMoney());
            }
            //将抢红包记录持久化
            storeEnvelopeRecordMapper.insert(storeEnvelopeRecord);
            //缓存更新
            redisUtil.set(redisRecordId, storeEnvelopeRecordVo);
            if (success){
                //小红包属性更新
                storeEnvelopeMapper.updateByPrimaryKey(storeEnvelope);
            }
        }finally {
            //释放锁
            lock.unlock();
        }
    }
}
