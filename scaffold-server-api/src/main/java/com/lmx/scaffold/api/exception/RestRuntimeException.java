package com.lmx.scaffold.api.exception;

import com.lmx.scaffold.commons.enums.Status;
import lombok.Data;

/**
 * @author:
 * @description: 全局rest异常异常
 * @date: 2020/4/10 18:19
 */
@Data
public class RestRuntimeException extends RuntimeException {

    protected Integer status;

    private String message;

    public RestRuntimeException(Status status) {
        this.status = status.getCode();
        this.message = status.getMsg();
    }

    public RestRuntimeException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestRuntimeException(String message, Integer status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public RestRuntimeException(String message, Throwable cause, Integer status, String message1) {
        super(message, cause);
        this.status = status;
        this.message = message1;
    }

    public RestRuntimeException(Throwable cause, Integer status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }

    public RestRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer status, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = message1;
    }
}
