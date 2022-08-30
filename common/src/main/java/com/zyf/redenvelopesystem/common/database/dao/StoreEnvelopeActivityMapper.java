package com.zyf.redenvelopesystem.common.database.dao;

import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelopeActivity;
import java.util.List;

public interface StoreEnvelopeActivityMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreEnvelopeActivity record);

    StoreEnvelopeActivity selectByPrimaryKey(String id);

    List<StoreEnvelopeActivity> selectAll();

    int updateByPrimaryKey(StoreEnvelopeActivity record);
}