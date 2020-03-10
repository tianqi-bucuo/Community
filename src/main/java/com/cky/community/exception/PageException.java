package com.cky.community.exception;

public class PageException extends RuntimeException {
    private String message;
    private Integer code;

    public PageException(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
