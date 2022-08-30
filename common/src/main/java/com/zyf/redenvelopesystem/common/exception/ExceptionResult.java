package com.zyf.redenvelopesystem.common.exception;

public class ExceptionResult {
    private int code;
    private String msg;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
        this.timestamp = System.currentTimeMillis();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
