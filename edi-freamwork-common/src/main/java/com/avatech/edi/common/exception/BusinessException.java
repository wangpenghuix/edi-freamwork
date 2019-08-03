package com.avatech.edi.common.exception;

/**
 * @author Fancy
 * @date 2019/7/6
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -3873063305424165075L;

    public BusinessException(){
        super();
    }

    public BusinessException(String coede,String message){
        this.code = coede;
        this.message = message;
    }

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
