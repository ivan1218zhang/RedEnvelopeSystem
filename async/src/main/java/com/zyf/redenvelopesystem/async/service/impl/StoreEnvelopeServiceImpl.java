package com.zyf.redenvelopesystem.async.service.impl;

import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeRecordVo;
import com.zyf.redenvelopesystem.async.service.StoreEnvelopeService;
import com.zyf.redenvelopesystem.common.redis.util.RedisUtil;
import com.zyf.redenvelopesystem.common.util.IdGenerator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StoreEnvelopeServiceImpl implements StoreEnvelopeService {
    private final RabbitTemplate rabbitTemplate;
    final
    RedisUtil redisUtil;

    public StoreEnvelopeServiceImpl(RedisUtil redisUtil, RabbitTemplate rabbitTemplate) {
        this.redisUtil = redisUtil;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public String fight(String activityId, String memberId) {
        //生成唯一键
        String key="ASYNC:FIGHT_RECORD_USER_KEY:"+activityId+"-"+memberId;
        //生成一个提交记录放入数据库 作为后续的流程运转
        String recordId= IdGenerator.getIdStr();
        //利用redis的setnx检查是否重复排队
        Boolean isNotRepeat=redisUtil.setNx(key,recordId,600);
        //如果没有重复排队 放入MQ
        if (isNotRepeat){
            //后续根据记录id查询抢红包状态
            //当前设置为排队状态并存入redis
            StoreEnvelopeRecordVo storeEnvelopeRecordVo =new StoreEnvelopeRecordVo();
            storeEnvelopeRecordVo.setId(recordId);
            storeEnvelopeRecordVo.setActivityId(activityId);
            storeEnvelopeRecordVo.setMemberId(memberId);
            storeEnvelopeRecordVo.setStatus(0);
            redisUtil.set("ASYNC:FIGHT_RECORD:"+recordId, storeEnvelopeRecordVo);
            //抢红包具体的业务
            Map<String,String> param=new HashMap<>();
            param.put("activityId",activityId);
            param.put("memberId",memberId);
            param.put("recordId",recordId);
            rabbitTemplate.convertAndSend("GetEnvelopeQueue",param);
        }else {
            recordId= (String) redisUtil.get(key);
        }
        //无论是否重复排队 都会返回唯一键
        return recordId;
    }
}
