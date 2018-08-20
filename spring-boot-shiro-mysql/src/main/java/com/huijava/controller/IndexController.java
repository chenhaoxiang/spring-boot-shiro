/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenhx
 * @version IndexController.java, v 0.1 2018-08-07 上午 11:32
 */
@RestController
public class IndexController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("test");
        return "test";
    }

    @GetMapping({"/index", ""})
    @ResponseBody
    public String index() {
        System.out.println("index");
        return "index";
    }

    @GetMapping("/403")
    public ModelAndView notAuthorized() {
        System.out.println("403");
        return new ModelAndView("403");
    }

    /**
     * IncorrectCredentialsException 密码错误的异常
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public String login(String username, String password) {
        System.out.println("登录...");
        //有加密的话，在这里将密码进行加密再传入
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        //subject.login(usernamepasswordToken)会自动调用doGetAuthenticationInfo()方法
        currentUser.login(token);
        return "success";
    }

    @GetMapping("/logout")
    public String logout() {
        System.out.println("登出...");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "logout success";
    }

}