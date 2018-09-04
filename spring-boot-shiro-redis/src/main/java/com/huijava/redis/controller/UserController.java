/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.controller;

import com.huijava.redis.server.ShiroService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserController {
    @Autowired
    private ShiroService shiroService;

    @GetMapping("/insert")
    public String insert() {
        log.info("insert...");
        return "insert";
    }

    @GetMapping("/update")
    public String update() {
        log.info("update...");
        return "update";
    }

    @GetMapping("/delete")
    public String delete() {
        log.info("delete...");
        return "delete";
    }

    @GetMapping("/select")
    public String select() {
        log.info("select...");
        return "select";
    }

    /**
     * 更新权限
     *
     * @return
     */
    @GetMapping("/updatePermission")
    @ResponseBody
    public String updatePermission() {
        log.info("select...");
        shiroService.updatePermission();
        return "updatePermission";
    }
}