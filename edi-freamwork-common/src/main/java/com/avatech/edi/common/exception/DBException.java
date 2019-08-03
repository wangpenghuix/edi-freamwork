package com.avatech.edi.common.exception;

/**
 * @author Fancy
 * @date 2019/8/3
 */
public class DBException extends RuntimeException{

    private static final long serialVersionUID = -6387966164482361720L;

    public DBException() {
    }

    public DBException(String message) {
        super(message);
        this.message = message;
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public DBException(Throwable cause) {
        super(cause);
    }

    public DBException(String code,String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

    private String code;
    public String getCode(){
        return code;
    }

    private String message;
    public String getMessage(){
        return message;
    }
}
