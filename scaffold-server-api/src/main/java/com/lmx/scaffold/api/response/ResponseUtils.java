package com.lmx.scaffold.api.response;

import com.lmx.scaffold.api.dto.PageInfo;
import com.lmx.scaffold.commons.enums.Status;

/**
 * @author:
 * @description: 响应工具类
 * @date: 2020/8/26 10:48
 */
public class ResponseUtils {


    public static Result success() {
        return new Result(Status.SUCCESS);
    }

    /**
     * return the data use Map format, for example, passing the value of key, value, passing a value
     * eg. "/user/add"  then return user name: zhangsan
     */
    public static Result success(Object object) {
        return new Result(Status.SUCCESS, object);
    }

    /**
     * return the data use Map format, for example, passing the value of key, value, passing a value
     * eg. "/user/add"  then return user name: zhangsan
     */
    public static Result success(Status status, Object object) {
        return new Result(status, object);
    }


    public static Result success(PageInfo<Object> pageInfo) {
        Result result = new Result(Status.SUCCESS);
        result.setData(pageInfo);
        return result;
    }

    /**
     * error handle
     *
     * @param code result code
     * @param msg  result message
     * @return error result code
     */
    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    /**
     * error handle
     *
     * @return error result code
     */
    public static Result error(Status status) {
        return new Result(status);
    }
}
