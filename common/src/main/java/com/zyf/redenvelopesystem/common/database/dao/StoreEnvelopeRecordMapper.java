package com.zyf.redenvelopesystem.common.database.dao;

import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelopeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreEnvelopeRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreEnvelopeRecord record);

    StoreEnvelopeRecord selectByPrimaryKey(String id);

    List<StoreEnvelopeRecord> selectAll();

    int updateByPrimaryKey(StoreEnvelopeRecord record);

    int selectSuccessCountByMemberIdAndActivityId(@Param("memberId") String memberId, @Param(("activityId")) String activityId);

    List<StoreEnvelopeRecord> selectSuccessByMemberId(String memberId);
}