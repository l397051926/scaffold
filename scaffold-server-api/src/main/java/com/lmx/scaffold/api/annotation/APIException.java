package com.lmx.scaffold.api.annotation;


import com.lmx.scaffold.commons.enums.Status;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:
 * @description: Controller层 异常注解
 * @date: 2020/6/11 10:13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APIException {

    Status value();

}
