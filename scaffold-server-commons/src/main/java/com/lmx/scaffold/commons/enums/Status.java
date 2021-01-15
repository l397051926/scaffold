
package com.lmx.scaffold.commons.enums;

/**
 *  status enum
 */
public enum Status {

    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400,"参数校验失败"),
    LOGIN_ERROR(403,"抱歉，你暂无权访问该页面，请联系管理员配置页面权限。"),
    ASSERT_ERROR(406, "数据内容非空校验异常"),
    SERVER_ERROR(500, "服务器内部错误"),
    HTTP_REQUEST_ERROR(510, "HTTP接口请求失败🙀"),

    REQUEST_PARAMS_NOT_VALID_ERROR(10001, "用户编码：{0} 已存在, 请重新输入！"),
    SQL_UNIQUE_INDEX_ERROR(10002, "根目录已存在😿"),


    ;

    private int code;
    private String msg;

    private Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
