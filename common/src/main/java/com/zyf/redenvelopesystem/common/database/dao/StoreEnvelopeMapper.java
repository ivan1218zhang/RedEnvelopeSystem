package com.zyf.redenvelopesystem.common.database.dao;

import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelope;
import java.util.List;

public interface StoreEnvelopeMapper {
    int deleteByPrimaryKey(String id);
    int insert(StoreEnvelope record);
    StoreEnvelope selectByPrimaryKey(String id);
    List<StoreEnvelope> selectAll();
    int updateByPrimaryKey(StoreEnvelope record);
    List<StoreEnvelope> selectByActivityId(String activityId);
    StoreEnvelope selectByRecordId(String recordId);
}