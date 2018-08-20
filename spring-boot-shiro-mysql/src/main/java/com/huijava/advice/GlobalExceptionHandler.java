/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.advice;

import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常的处理
 * 通过@ExceptionHandler方法捕获特定异常。
 * 默认情况下@ControllerAdvice监控所有的@RequestMapping方法，也可以指定监听过滤的条件。
 * @author chenhx
 * @version ShiroExceptionHandler.java, v 0.1 2018-08-20 上午 11:35
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * UnknownAccountException未经过授权的例外情况异常（在未经登录，访问需要授权的链接）
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(UnknownAccountException.class)
    public ModelAndView handleUnknownAccountException(HttpServletRequest request, Exception ex) {
        System.out.println("未经过授权的例外情况----handleUnknownAccountException");
        return new ModelAndView("403");
    }
}