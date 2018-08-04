/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.helloworld.controller;

import com.huijava.helloworld.entity.Users;
import com.huijava.helloworld.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenhx
 * @version UserController.java, v 0.1 2018-08-03 下午 2:47
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

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


    @GetMapping("/insert")
    @RequiresPermissions("user:insert")
    public Integer insert() {
        System.out.println("insert...");
        Users users = new Users();
        users.setUsername("test");
        users.setPassword("1234");
        return userService.addUser(users);
    }

    @GetMapping("/update")
    @RequiresPermissions("user:update")
    public Integer update() {
        System.out.println("update...");
        Users users = new Users();
        users.setUsername("test");
        users.setPassword("1234");
        return userService.updateUser(users);
    }

    @GetMapping("/delete")
    @RequiresPermissions("user:delete")
    public Integer delete() {
        System.out.println("delete...");
        Users users = new Users();
        users.setUsername("test");
        users.setPassword("1234");
        return userService.deleteUser(users);
    }

    @GetMapping("/select")
    @RequiresPermissions("user:select")
    public Users select(String username) {
        System.out.println("select...");
        return userService.selectUser(username);
    }

    @GetMapping("/403")
    public ModelAndView notAuthorized() {
        System.out.println("403");
        return new ModelAndView("403");
    }

    @GetMapping({"/index", ""})
    @ResponseBody
    public String index() {
        System.out.println("index");
        return "index";
    }
}