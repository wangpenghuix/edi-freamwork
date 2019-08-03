package com.avatech.edi.common.exception;

/**
 * @author Fancy
 * @date 2019/8/3
 */
public class BusinessObjectException extends RuntimeException{

    private static final long serialVersionUID = 911511857000937951L;

    private String code = "";

    public String getCode(){
        return code;
    }

    private String message="";

    public String getMessage(){return message;}



    public BusinessObjectException() {
    }

    public BusinessObjectException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessObjectException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessObjectException(Throwable cause) {
        super(cause);
    }

    public BusinessObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

    public BusinessObjectException(String code,String message){
        super(message);
        this.code = code;
        this.message = message;
    }

}
