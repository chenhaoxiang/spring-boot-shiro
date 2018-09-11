/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.springbootcustompermission.config;

import com.uifuture.springbootcustompermission.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenhx
 * @version MVCConfig.java, v 0.1 2018-09-11 上午 11:38
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor())
                .excludePathPatterns("/static/*")
                .excludePathPatterns("/error")
                .excludePathPatterns("/login")
                .addPathPatterns("/**");
    }

}