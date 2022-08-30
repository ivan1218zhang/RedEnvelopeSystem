package com.zyf.redenvelopesystem.common.vo;

import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelopeRecord;

public class StoreEnvelopeRecordVo {
    private String id;
    private String activityId;
    private String memberId;
    private Integer status;
    private double money;

    public StoreEnvelopeRecordVo() {
    }

    public StoreEnvelopeRecordVo(StoreEnvelopeRecord storeEnvelopeRecord) {
        if (storeEnvelopeRecord==null){
            return;
        }
        this.id=storeEnvelopeRecord.getId();
        this.activityId=storeEnvelopeRecord.getActivityId();
        this.memberId=storeEnvelopeRecord.getMemberId();
        this.status=storeEnvelopeRecord.getStatus();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
