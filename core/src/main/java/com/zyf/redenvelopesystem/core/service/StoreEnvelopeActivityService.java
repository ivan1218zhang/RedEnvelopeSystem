package com.zyf.redenvelopesystem.core.service;

import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeActivityVo;

import java.util.Date;

public interface StoreEnvelopeActivityService {
    Double refund(String activityId);
    StoreEnvelopeActivityVo send(String storeId, double totalMoney, int totalNumber, Date startTime);
}
