package com.lmx.scaffold.api.response;

import com.lmx.scaffold.commons.enums.Status;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:
 * @description: 响应数据
 * @date: 2020/8/20 17:23
 */
@Data
public class Result<T> implements Serializable {

    /**
     * status
     */
    private Integer statusCode;

    /**
     * message
     */
    private String message;

    /**
     * data
     */
    private T data;

    public Result() {
    }

    public Result(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }


    public Result(Status status, T data) {
        this.statusCode = status.getCode();
        this.message = status.getMsg();
        this.data = data;
    }

    public Result(Status status) {
        this.statusCode = status.getCode();
        this.message = status.getMsg();
    }

    public Result success(T data) {
        this.statusCode = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getMsg();
        this.data = data;
        return this;
    }

    public boolean successful() {
        return this.statusCode == 200;
    }

    public boolean failed() {
        return !successful();
    }

}
