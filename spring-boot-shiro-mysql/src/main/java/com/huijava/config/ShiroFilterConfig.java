/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * shiro的权限拦截器map设置
 *
 * @author chenhx
 * @version ShiroFilterConfig.java, v 0.1 2018-08-20 上午 10:43
 */
@Configuration
@PropertySource("classpath:shiro/shiro.properties")
public class ShiroFilterConfig {
    /**
     * @Fields anon : 不进行拦截的权限
     */
    @Value("#{'${shiro.filter.list.anon}'.split(',')}")
    private List<String> anon;
    /**
     * 是否在最后添加全部链接需要认证
     */
    @Value("${shiro.filter.allUrlAuthentication}")
    private boolean allUrlAuthentication;

    /**
     * Getter method for property <tt>anon</tt>.
     *
     * @return property value of anon
     */
    public List<String> getAnon() {
        return anon;
    }

    /**
     * Setter method for property <tt>anon</tt>.
     *
     * @param anon value to be assigned to property anon
     */
    public void setAnon(List<String> anon) {
        this.anon = anon;
    }

    /**
     * Getter method for property <tt>allUrlAuthentication</tt>.
     *
     * @return property value of allUrlAuthentication
     */
    public boolean isAllUrlAuthentication() {
        return allUrlAuthentication;
    }

    /**
     * Setter method for property <tt>allUrlAuthentication</tt>.
     *
     * @param allUrlAuthentication value to be assigned to property allUrlAuthentication
     */
    public void setAllUrlAuthentication(boolean allUrlAuthentication) {
        this.allUrlAuthentication = allUrlAuthentication;
    }
}