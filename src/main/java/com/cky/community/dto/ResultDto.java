package com.cky.community.dto;

import com.cky.community.exception.ErrorCode;
import com.cky.community.exception.PageException;

public class ResultDto {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public static ResultDto errorOf(Integer code,String message){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(ErrorCode errorCode){
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDto errorOf(PageException e){
        return errorOf(e.getCode(),e.getMessage());
    }

    public static ResultDto okOf() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }
}
