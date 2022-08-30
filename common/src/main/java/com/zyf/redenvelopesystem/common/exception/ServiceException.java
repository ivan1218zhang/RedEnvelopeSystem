package com.zyf.redenvelopesystem.common.exception;

public class ServiceException extends RuntimeException{
    private ExceptionEnum exceptionEnum;

    public ServiceException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum=exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
