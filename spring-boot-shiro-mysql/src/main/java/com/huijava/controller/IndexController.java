/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhx
 * @version IndexController.java, v 0.1 2018-08-07 上午 11:32
 */
@RestController
public class IndexController {

    @GetMapping("/login")
    public void login(String username, String password) {
        System.out.println("登录...");
        //有加密的话，在这里将密码进行加密再传入
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("UnknownAccountException -- > 账号不存在：");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            System.out.println("IncorrectCredentialsException -- > 密码不正确：");
            e.printStackTrace();
        }
    }

    @GetMapping("/logout")
    public void logout() {
        System.out.println("登出...");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    /**
     * TODO 注册
     */
    @PostMapping("/register")
    public void register() {
        System.out.println("注册，");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

}