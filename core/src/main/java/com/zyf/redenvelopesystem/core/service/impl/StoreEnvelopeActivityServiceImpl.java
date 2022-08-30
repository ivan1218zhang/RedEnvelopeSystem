package com.zyf.redenvelopesystem.core.service.impl;

import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeActivityVo;
import com.zyf.redenvelopesystem.common.database.dao.MemberMapper;
import com.zyf.redenvelopesystem.common.database.dao.StoreEnvelopeActivityMapper;
import com.zyf.redenvelopesystem.common.database.dao.StoreEnvelopeMapper;
import com.zyf.redenvelopesystem.common.database.dao.StoreMapper;
import com.zyf.redenvelopesystem.common.database.dto.Member;
import com.zyf.redenvelopesystem.common.database.dto.Store;
import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelope;
import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelopeActivity;
import com.zyf.redenvelopesystem.common.util.IdGenerator;
import com.zyf.redenvelopesystem.common.exception.ExceptionEnum;
import com.zyf.redenvelopesystem.common.exception.ServiceException;
import com.zyf.redenvelopesystem.core.service.StoreEnvelopeActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
@Service
public class StoreEnvelopeActivityServiceImpl implements StoreEnvelopeActivityService {
    final
    MemberMapper memberMapper;
    final
    StoreMapper storeMapper;
    final
    StoreEnvelopeActivityMapper storeEnvelopeActivityMapper;
    final
    StoreEnvelopeMapper storeEnvelopeMapper;

    public StoreEnvelopeActivityServiceImpl(StoreEnvelopeActivityMapper storeEnvelopeActivityMapper, MemberMapper memberMapper, StoreMapper storeMapper, StoreEnvelopeMapper storeEnvelopeMapper) {
        this.storeEnvelopeActivityMapper = storeEnvelopeActivityMapper;
        this.memberMapper = memberMapper;
        this.storeMapper = storeMapper;
        this.storeEnvelopeMapper = storeEnvelopeMapper;
    }
    @Override
    @Transactional(rollbackFor=Exception.class,isolation = Isolation.SERIALIZABLE)
    public Double refund(String activityId){
        //将需要的信息匹配出来
        StoreEnvelopeActivity storeEnvelopeActivity=storeEnvelopeActivityMapper.selectByPrimaryKey(activityId);
        Store store=storeMapper.selectByPrimaryKey(storeEnvelopeActivity.getStoreId());
        Member member=memberMapper.selectByPrimaryKey(store.getMemberId());
        if (storeEnvelopeActivity.getStatus()==1){
            throw new ServiceException(ExceptionEnum.ACTIVITY_IS_INACTIVE);
        }
        //先结束活动
        storeEnvelopeActivity.setStatus(1);
        storeEnvelopeActivityMapper.updateByPrimaryKey(storeEnvelopeActivity);
        //计算退款金额
        List<StoreEnvelope> storeEnvelopes=storeEnvelopeMapper.selectByActivityId(activityId);
        Double money=0.0;
        for (StoreEnvelope storeEnvelope:storeEnvelopes){
            if (storeEnvelope.getRecordId()==null){
                money+=storeEnvelope.getMoney();
            }
        }
        //给账户增加退款金额
        member.setMoney(member.getMoney()+money);
        memberMapper.updateByPrimaryKey(member);
        return money;
    }

    @Override
    @Transactional(rollbackFor=Exception.class,isolation = Isolation.SERIALIZABLE)
    public StoreEnvelopeActivityVo send(String storeId, double totalMoney, int totalNumber, Date startTime){
        //检查用户余额是否够
        Store store=storeMapper.selectByPrimaryKey(storeId);
        Member member=memberMapper.selectByPrimaryKey(store.getMemberId());
        if (member.getMoney()<totalMoney){
            throw new ServiceException(ExceptionEnum.ACCOUNT_BALANCE_IS_INSUFFICIENT);
        }
        //创建红包活动
        StoreEnvelopeActivity storeEnvelopeActivity=new StoreEnvelopeActivity();
        storeEnvelopeActivity.setId(IdGenerator.getIdStr());
        storeEnvelopeActivity.setStoreId(storeId);
        storeEnvelopeActivity.setTotalMoney(totalMoney);
        storeEnvelopeActivity.setTotalNumber(totalNumber);
        storeEnvelopeActivity.setStartTime(startTime);
        storeEnvelopeActivity.setStatus(0);
        storeEnvelopeActivityMapper.insert(storeEnvelopeActivity);
        //待分配金额为总金额减去保底金额 结果再乘100方便random
        int rest=(int)(totalMoney*100)-totalNumber;
        Random random=new Random(System.currentTimeMillis());
        //预先拆分成小红包
        for (int i=0;i<totalNumber;i++){
            //分配金额 在0到2倍人均中随机金额 最后一个小红包就是剩下的钱
            int rand;
            if (i==totalNumber-1){
                rand=rest;
            }else {
                rand=random.nextInt(2*(2*rest+totalNumber-i)/(2*(totalNumber-i)));
            }
            double money= (rand+1)/100.0;
            //小红包插入数据库
            StoreEnvelope storeEnvelope=new StoreEnvelope();
            storeEnvelope.setId(IdGenerator.getIdStr());
            storeEnvelope.setActivityId(storeEnvelopeActivity.getId());
            storeEnvelope.setMoney(money);
            storeEnvelopeMapper.insert(storeEnvelope);
            //剩下的钱减去这次分配的钱
            rest-=rand;
        }
        //扣除余额
        member.setMoney(member.getMoney()-totalMoney);
        memberMapper.updateByPrimaryKey(member);
        return new StoreEnvelopeActivityVo(storeEnvelopeActivity);
    }
}
