/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.springbootcustompermission.controller;

import com.uifuture.springbootcustompermission.constants.PermissionConstants;
import com.uifuture.springbootcustompermission.entity.User;
import com.uifuture.springbootcustompermission.enums.RequiredPermission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenhx
 * @version ProductController.java, v 0.1 2018-09-11 上午 11:46
 */
@RestController
@RequiredPermission("product")
public class ProductController {

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {
        if (user.getUsername().equals("admin")) {
            user.setRoleId(1);
        } else {
            user.setRoleId(2);
        }
        request.getSession().setAttribute(PermissionConstants.LOGIN_USER_SESSION, user);
        return "success";
    }

    /**
     * @return
     */
    @RequestMapping("/list")
    @RequiredPermission("product.list")
    public String list() {
        // 省略产品列表查询逻辑
        return "/product/list";
    }

    /**
     * 详情
     *
     * @return
     */
    @RequestMapping("/detail")
    @RequiredPermission("product.edit")
    public String detail() {
        // 省略查询产品详情的逻辑
        return "/product/edit";
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("/delete")
    @RequiredPermission("product.delete")
    public String delete() {
        // 省略删除产品的逻辑
        return "/product/delete";
    }


    @RequestMapping("/select")
    public String select() {
        // 省略删除产品的逻辑
        return "/product/select";
    }

    /**
     * @return
     */
    @RequestMapping("/error/403")
    public String error403() {
        return "error403";
    }

}