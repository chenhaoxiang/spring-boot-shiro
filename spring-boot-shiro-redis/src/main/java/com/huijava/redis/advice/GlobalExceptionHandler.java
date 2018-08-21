/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.advice;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常的处理
 * 通过@ExceptionHandler方法捕获特定异常。
 * 默认情况下@ControllerAdvice监控所有的@RequestMapping方法，也可以指定监听过滤的条件。
 * HIGHEST_PRECEDENCE - 设置优先级最高
 *
 * @author chenhx
 * @version ShiroExceptionHandler.java, v 0.1 2018-08-20 上午 11:35
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    /**
     * UnknownAccountException未经过授权的例外情况异常（在未经登录，访问需要授权的链接）
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler({UnknownAccountException.class})
    public ModelAndView handleUnknownAccountException(HttpServletRequest request, Exception ex) {
        System.out.println("未经过授权的例外情况----handleUnknownAccountException");
        return new ModelAndView("root/login");
    }

    /**
     * IncorrectCredentialsException 密码错误
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler({IncorrectCredentialsException.class})
    public ModelAndView handleIncorrectCredentialsException(HttpServletRequest request, Exception ex) {
        System.out.println("密码错误----handleIncorrectCredentialsException");
        return new ModelAndView("root/login");
    }
}