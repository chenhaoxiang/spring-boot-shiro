/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.controller;

import com.huijava.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhx
 * @version UserController.java, v 0.1 2018-08-07 上午 11:32
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/insert")
    public String insert() {
        System.out.println("insert...");
        return "insert";
    }

    @GetMapping("/update")
    public String update() {
        System.out.println("update...");
        return "update";
    }

    @GetMapping("/delete")
    public String delete() {
        System.out.println("delete...");
        return "delete";
    }

    @GetMapping("/select")
    public String select() {
        System.out.println("select...");
        return "select";
    }

    /**
     * @return
     */
    @GetMapping("/updatePermission")
    @ResponseBody
    public String updatePermission() {
        System.out.println("select...");
        return "success";
    }
}