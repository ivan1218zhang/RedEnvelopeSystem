package com.zyf.redenvelopesystem.common.exception;

public enum ExceptionEnum {
    //  类似单例，这相当于new一个对象，枚举的构造方法默认私有
    ACCOUNT_BALANCE_IS_INSUFFICIENT("用户余额不足", 400),
    ACTIVITY_IS_INACTIVE("活动已经退款过了",401)
    ;

    private String msg;
    private int code;

    ExceptionEnum() {
    }

    ExceptionEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
