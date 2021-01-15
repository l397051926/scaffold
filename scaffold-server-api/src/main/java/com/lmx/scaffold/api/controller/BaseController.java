
package com.lmx.scaffold.api.controller;

import com.lmx.scaffold.api.dto.PageInfo;
import com.lmx.scaffold.api.exception.RestRuntimeException;
import com.lmx.scaffold.api.response.Result;
import com.lmx.scaffold.commons.constant.Constants;
import com.lmx.scaffold.commons.enums.Status;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;


/**
 * base controller
 */
public class BaseController<T> {

    /**
     * check params
     *
     * @param pageNo   page number
     * @param pageSize page size
     * @return check result code
     */
    public void checkPageParams(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            throw new RestRuntimeException(Status.REQUEST_PARAMS_NOT_VALID_ERROR.getCode(), MessageFormat.format(Status.REQUEST_PARAMS_NOT_VALID_ERROR.getMsg(), Constants.PAGE_NUMBER));
        }
        if (pageSize <= 0) {
            throw new RestRuntimeException(Status.REQUEST_PARAMS_NOT_VALID_ERROR.getCode(), MessageFormat.format(Status.REQUEST_PARAMS_NOT_VALID_ERROR.getMsg(), Constants.PAGE_NUMBER));
        }
    }

    /**
     * get ip address in the http request
     *
     * @param request http servlet request
     * @return client ip address
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String clientIp = request.getHeader(Constants.HTTP_X_FORWARDED_FOR);

        if (StringUtils.isNotEmpty(clientIp) && !StringUtils.equalsIgnoreCase(Constants.HTTP_HEADER_UNKNOWN, clientIp)) {
            int index = clientIp.indexOf(Constants.COMMA);
            if (index != -1) {
                return clientIp.substring(0, index);
            } else {
                return clientIp;
            }
        }

        clientIp = request.getHeader(Constants.HTTP_X_REAL_IP);
        if (StringUtils.isNotEmpty(clientIp) && !StringUtils.equalsIgnoreCase(Constants.HTTP_HEADER_UNKNOWN, clientIp)) {
            return clientIp;
        }

        return request.getRemoteAddr();
    }


    /**
     * success
     *
     * @return success result code
     */
    public Result success() {
        return new Result(Status.SUCCESS);
    }


    public Result success(Object t) {
        return getResult(Status.SUCCESS.getMsg(), t);
    }

    /**
     * return the data use Map format, for example, passing the value of key, value, passing a value
     * eg. "/user/add"  then return user name: zhangsan
     *
     * @param msg    message
     * @param object success object data
     * @return success result code
     */
    public Result success(String msg, Map<String, Object> object) {
        Result result = getResult(msg, object);
        return result;
    }


    public Result success(PageInfo<T> pageInfo) {
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
    public Result error(Integer code, String msg) {
        Result result = new Result();
        result.setStatusCode(code);
        result.setMessage(msg);
        return result;
    }

    /**
     * put message to result object
     *
     * @param result       result
     * @param status       status
     * @param statusParams status parameters
     */
    protected void putMsg(Result result, Status status, Object... statusParams) {
        result.setStatusCode(status.getCode());

        if (statusParams != null && statusParams.length > 0) {
            result.setMessage(MessageFormat.format(status.getMsg(), statusParams));
        } else {
            result.setMessage(status.getMsg());
        }

    }

    /**
     * get result
     *
     * @param msg  message
     * @param list object list
     * @return result code
     */
    private Result getResult(String msg, Object list, Status status) {
        Result result = new Result();
        result.setStatusCode(status.getCode());
        result.setMessage(msg);

        result.setData(list);
        return result;
    }

    /**
     * get result
     *
     * @param msg  message
     * @param list object list
     * @return result code
     */
    private Result getResult(String msg, Object list) {
        Result result = new Result();
        result.setStatusCode(Status.SUCCESS.getCode());
        result.setMessage(msg);
        result.setData(list);
        return result;
    }

    /**
     * error handle
     *
     * @param status
     * @return
     */
    public Result error(Status status) {
        Result result = new Result();
        result.setStatusCode(status.getCode());
        result.setMessage(status.getMsg());
        return result;
    }


}
