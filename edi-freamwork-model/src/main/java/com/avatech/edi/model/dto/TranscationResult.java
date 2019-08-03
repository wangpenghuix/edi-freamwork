package com.avatech.edi.model.dto;

/**
 * @author Fancy
 * @date 2019/8/3
 */
public class TranscationResult {

    private String errorCode;

    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
