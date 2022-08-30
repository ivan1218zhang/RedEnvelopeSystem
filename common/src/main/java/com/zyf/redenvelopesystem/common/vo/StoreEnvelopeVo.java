package com.zyf.redenvelopesystem.common.vo;

import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelope;

import java.util.Date;

public class StoreEnvelopeVo {
    private String id;

    private String recordId;

    private String activityId;

    private Double money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public StoreEnvelopeVo(StoreEnvelope storeEnvelope) {
        this.id = storeEnvelope.getId();
        this.recordId = storeEnvelope.getRecordId();
        this.activityId = storeEnvelope.getActivityId();
        this.money = storeEnvelope.getMoney();
    }
}