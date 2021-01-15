package com.lmx.scaffold.api.aspect;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class RequestInfo {
	private String ip;
	private String url;
	private String httpMethod;
	private String classMethod;
	private Object requestParams;
	@JSONField(serialize = false)
	private Object result;
	private Long timeCost;
}
