/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.controller;

import com.huijava.redis.entity.TUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author chenhx
 * @version IndexController.java, v 0.1 2018-08-07 上午 11:32
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        LOGGER.info("test...");
        return "test";
    }

    @GetMapping({"/index", ""})
    public ModelAndView index(Model model) {
        TUser user = (TUser) SecurityUtils.getSubject().getPrincipal();
        LOGGER.info("index---user={}", user);
        model.addAttribute("user", user);
        return new ModelAndView("root/index");
    }

    @GetMapping("/403")
    public ModelAndView notAuthorized() {
        LOGGER.info("notAuthorized...");
        return new ModelAndView("error/403");
    }

    /**
     * IncorrectCredentialsException 密码错误的异常
     * shiro自带的登录成功跳转没用，这里重定向到首页
     *
     * @param user
     */
    @PostMapping("/toLogin")
    public RedirectView toLogin(TUser user, boolean rememberMe) {
        LOGGER.info("登录...user={},rememberMe={}", user, rememberMe);
        //有加密的话，在这里将密码进行加密再传入
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(rememberMe);
        Subject currentUser = SecurityUtils.getSubject();
        //subject.login(usernamepasswordToken)会自动调用doGetAuthenticationInfo()方法
        currentUser.login(token);
        return new RedirectView("index");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        LOGGER.info("登录页面...");
        return new ModelAndView("root/login");
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        LOGGER.info("登出...");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "logout success";
    }

}