/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.controller;

import com.huijava.entity.TUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenhx
 * @version IndexController.java, v 0.1 2018-08-07 上午 11:32
 */
@Controller
public class IndexController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("test");
        return "test";
    }

    @GetMapping({"/index", ""})
    public ModelAndView index() {
        System.out.println("index");
        return new ModelAndView("root/index");
    }

    @GetMapping("/403")
    public ModelAndView notAuthorized() {
        System.out.println("403");
        return new ModelAndView("error/403");
    }

    /**
     * IncorrectCredentialsException 密码错误的异常
     * @param user
     * @param request
     */
    @PostMapping("/toLogin")
    @ResponseBody
    public void toLogin(TUser user, HttpServletRequest request) {
        System.out.println("登录...");
        //有加密的话，在这里将密码进行加密再传入
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        //subject.login(usernamepasswordToken)会自动调用doGetAuthenticationInfo()方法
        currentUser.login(token);
        request.getSession().setAttribute("username", user.getUsername());
    }

    @GetMapping("/login")
    public ModelAndView login() {
        System.out.println("登录页面...");
        return new ModelAndView("root/login");
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        System.out.println("登出...");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "logout success";
    }

}