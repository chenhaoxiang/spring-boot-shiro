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
     * 登录页面
     */
    @Value("${shiro.filter.loginUrl}")
    private String loginUrl;
    /**
     * 登录成功后跳转页面
     */
    @Value("${shiro.filter.successUrl}")
    private String successUrl;
    /**
     * 无权限跳转的页面
     */
    @Value("${shiro.filter.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * Getter method for property <tt>loginUrl</tt>.
     *
     * @return property value of loginUrl
     */
    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * Setter method for property <tt>loginUrl</tt>.
     *
     * @param loginUrl value to be assigned to property loginUrl
     */
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * Getter method for property <tt>successUrl</tt>.
     *
     * @return property value of successUrl
     */
    public String getSuccessUrl() {
        return successUrl;
    }

    /**
     * Setter method for property <tt>successUrl</tt>.
     *
     * @param successUrl value to be assigned to property successUrl
     */
    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    /**
     * Getter method for property <tt>unauthorizedUrl</tt>.
     *
     * @return property value of unauthorizedUrl
     */
    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    /**
     * Setter method for property <tt>unauthorizedUrl</tt>.
     *
     * @param unauthorizedUrl value to be assigned to property unauthorizedUrl
     */
    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

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