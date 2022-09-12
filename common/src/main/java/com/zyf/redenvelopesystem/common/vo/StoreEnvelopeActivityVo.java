package com.zyf.redenvelopesystem.common.vo;

import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelopeActivity;

public class StoreEnvelopeActivityVo {
    private String id;

    private String storeId;

    private Integer totalNumber;

    private Double totalMoney;

    private Integer status;
    private Long startTime;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }


    public StoreEnvelopeActivityVo() {

    }
    public StoreEnvelopeActivityVo(StoreEnvelopeActivity storeEnvelopeActivity) {
        this.id = storeEnvelopeActivity.getId();
        this.storeId = storeEnvelopeActivity.getStoreId();
        this.totalNumber = storeEnvelopeActivity.getTotalNumber();
        this.totalMoney = storeEnvelopeActivity.getTotalMoney();
        this.status=storeEnvelopeActivity.getStatus();
        this.startTime = storeEnvelopeActivity.getStartTime().getTime();
    }
}