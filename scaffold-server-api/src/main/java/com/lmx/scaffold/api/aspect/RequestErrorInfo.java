package com.lmx.scaffold.api.aspect;

import lombok.Data;

/**
 * aop错误日志实体
 */
@Data
public class RequestErrorInfo {
	private String ip;
	private String url;
	private String httpMethod;
	private String classMethod;
	private Object requestParams;
	private RuntimeException exception;
}
