package com.zyf.redenvelopesystem.query.service.impl;

import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeVo;
import com.zyf.redenvelopesystem.common.database.dao.StoreEnvelopeMapper;
import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelope;
import com.zyf.redenvelopesystem.query.service.StoreEnvelopeService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service
public class StoreEnvelopeServiceImpl implements StoreEnvelopeService {
    final
    StoreEnvelopeMapper storeEnvelopeMapper;

    public StoreEnvelopeServiceImpl(StoreEnvelopeMapper storeEnvelopeMapper) {
        this.storeEnvelopeMapper = storeEnvelopeMapper;
    }

    @Override
    public Integer queryActivityRestCount(String activityId) {
        List<StoreEnvelope> storeEnvelopes=storeEnvelopeMapper.selectByActivityId(activityId);
        Integer sum=0;
        for (StoreEnvelope storeEnvelope:storeEnvelopes){
            if (storeEnvelope.getRecordId()==null){
                sum++;
            }
        }
        return sum;
    }
}
