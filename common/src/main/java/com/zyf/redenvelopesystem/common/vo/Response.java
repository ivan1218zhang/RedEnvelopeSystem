package com.zyf.redenvelopesystem.common.vo;

import com.zyf.redenvelopesystem.common.exception.ExceptionResult;
import com.zyf.redenvelopesystem.common.exception.ServiceException;

public class Response<E> {
    int code;
    String msg;
    E data;
    long timestamp;

    public Response<E> success(E data){
        this.data=data;
        this.code=0;
        this.msg="success";
        this.timestamp=System.currentTimeMillis();
        return this;
    }
    public Response<E> error(ServiceException serviceException){
        this.code=serviceException.getExceptionEnum().getCode();
        this.msg=serviceException.getExceptionEnum().getMsg();
        this.data=null;
        this.timestamp=System.currentTimeMillis();
        return this;
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

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
