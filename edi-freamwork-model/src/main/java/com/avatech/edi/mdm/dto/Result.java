package com.avatech.edi.mdm.dto;

import jdk.internal.util.xml.impl.ReaderUTF8;

public class Result<T> {
    private static final String OK = "0";
    private static final String MESSAGE = "操作成功";

    public static Result ok(){
        Result rt = new Result();
        rt.code = OK;
        rt.message = MESSAGE;
        return rt;
    }
    public Result ok(T data){
        this.code = OK;
        this.message = MESSAGE;
        this.data = data;
        return this;
    }

    public Result error(String code,String message){
        this.code = code;
        this.message = message;
        return this;
    }

    public Result error(String code,Exception e){
        this.code = code;
        this.message = e.getMessage();
        return this;
    }

    private String code;

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    private String message;

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    private T data;

    public T getData(){
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
