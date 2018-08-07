/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.helloworld.dao;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * 模拟从数据库中获取用户信息
 *
 * @author chenhx
 * @version UserDao.java, v 0.1 2018-08-02 下午 4:55
 */
@Repository
public class UserDao {

    /**
     * 通过用户名获取用户的所有角色名
     *
     * @param username
     * @return
     */
    public Set<String> selectRolesByUsername(String username) {
        Set<String> roles = new HashSet<>();
        switch (username) {
            case "chenhx": {
                roles.add("admin");
                break;
            }
            case "user": {
                roles.add("user");
                break;
            }
        }
        return roles;
    }

    /**
     * 通过角色名获取权限
     *
     * @param role
     * @return
     */
    public Set<String> selectPermissionsByRole(String role) {
        Set<String> permissions = new HashSet<>();
        switch (role) {
            case "admin":
                permissions.add("user:*");
                break;
            case "user":
                permissions.add("user:insert");
                permissions.add("user:select");
                break;
        }
        return permissions;
    }

    /**
     * 用户进行登录
     *
     * @param username
     * @return
     */
    public String selectPasswordByUsername(String username) {
        switch (username) {
            case "chenhx":
                return "1234";
            case "user":
                return "1234";
        }
        return null;
    }

}