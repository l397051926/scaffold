package com.lmx.scaffold.api.configurations;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FilterChainConfig {

    private String[] ignorePatternArray;

    @PostConstruct
    public void init() {

        //拦截器 开放接口
        ignorePatternArray = new String[]{
                "/swagger-resources/**",
                "/swagger-ui.html*",
                "/webjars/**",
                "/v2/**",
                "/doc.html",
                "*.html",
                "/ui/**",
                //测试接口
                "/sys/**"
        };
    }


    public String[] getIgnorePatternArray() {
        return ignorePatternArray;
    }


}
