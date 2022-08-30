package com.zyf.redenvelopesystem.query.service.impl;

import com.zyf.redenvelopesystem.common.database.dao.StoreEnvelopeMapper;
import com.zyf.redenvelopesystem.common.database.dao.StoreEnvelopeRecordMapper;
import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelope;
import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelopeRecord;
import com.zyf.redenvelopesystem.common.redis.util.RedisUtil;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeRecordVo;
import com.zyf.redenvelopesystem.query.service.StoreEnvelopeRecordService;
import org.springframework.stereotype.Service;

@Service
public class StoreEnvelopeRecordServiceImpl implements StoreEnvelopeRecordService {
    final
    StoreEnvelopeMapper storeEnvelopeMapper;
    final
    StoreEnvelopeRecordMapper storeEnvelopeRecordMapper;
    final
    RedisUtil redisUtil;

    public StoreEnvelopeRecordServiceImpl(StoreEnvelopeRecordMapper storeEnvelopeRecordMapper, RedisUtil redisUtil, StoreEnvelopeMapper storeEnvelopeMapper) {
        this.storeEnvelopeRecordMapper = storeEnvelopeRecordMapper;
        this.redisUtil = redisUtil;
        this.storeEnvelopeMapper = storeEnvelopeMapper;
    }

    @Override
    public StoreEnvelopeRecordVo queryById(String recordId) {
        String redisRecordId="ASYNC:FIGHT_RECORD:"+recordId;
        //先查redis
        StoreEnvelopeRecordVo storeEnvelopeRecordVo = (StoreEnvelopeRecordVo) redisUtil.get(redisRecordId);
        if (storeEnvelopeRecordVo !=null){
            return storeEnvelopeRecordVo;
        }
        //没有再查mysql
        StoreEnvelopeRecord storeEnvelopeRecord = storeEnvelopeRecordMapper.selectByPrimaryKey(recordId);
        if (storeEnvelopeRecord==null){
            return null;
        }
        storeEnvelopeRecordVo =new StoreEnvelopeRecordVo(storeEnvelopeRecord);
        if (storeEnvelopeRecordVo.getStatus()==1){
            StoreEnvelope storeEnvelope=storeEnvelopeMapper.selectByRecordId(recordId);
            storeEnvelopeRecordVo.setMoney(storeEnvelope.getMoney());
        }
        redisUtil.set(redisRecordId, storeEnvelopeRecordVo);
        return storeEnvelopeRecordVo;
    }
}
