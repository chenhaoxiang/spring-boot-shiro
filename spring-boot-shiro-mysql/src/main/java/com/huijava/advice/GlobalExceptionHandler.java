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
 *
 * @author chenhx
 * @version ShiroExceptionHandler.java, v 0.1 2018-08-20 上午 11:35
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 未经过授权的例外情况
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