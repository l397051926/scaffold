package com.lmx.scaffold.api.configurations;

import com.lmx.scaffold.api.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @author:
 * @description:
 * @date: 2020/8/20 12:47
 */
@Configuration
public class GlobalConfiguration implements WebMvcConfigurer {

    public static final String LOGIN_INTERCEPTOR_PATH_PATTERN = "/**/*";

    @Bean
    public LogInterceptor loginInterceptor() {
        return new LogInterceptor();
    }

    @Autowired
    FilterChainConfig filterChainConfig;

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        /**  **/
        lci.setParamName("language");

        return lci;
    }

    /**
     * 拦截器的配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //i18n
        registry.addInterceptor(localeChangeInterceptor());

        registry.addInterceptor(loginInterceptor())
                .addPathPatterns(LOGIN_INTERCEPTOR_PATH_PATTERN)
                .excludePathPatterns(filterChainConfig.getIgnorePatternArray());

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/ui/**").addResourceLocations("file:ui/");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
