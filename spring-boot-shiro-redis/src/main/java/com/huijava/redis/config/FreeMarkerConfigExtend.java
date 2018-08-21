/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

/**
 * 继承FreeMarkerConfigurer，进行响应的扩展
 *
 * @author chenhx
 * @version FreeMarkerConfigExtend.java, v 0.1 2018-08-20 下午 4:05
 */
@Configuration
public class FreeMarkerConfigExtend {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
     */
    @PostConstruct
    public void setSharedVariable() {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}