package com.zyf.redenvelopesystem.query.service;

import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeRecordVo;

import java.util.List;

public interface StoreEnvelopeRecordService {
    StoreEnvelopeRecordVo queryById(String recordId);

    List<StoreEnvelopeRecordVo> querySuccessByMemberId(String memberId);
}
