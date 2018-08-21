/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.server;

import com.huijava.redis.entity.TPermission;
import com.huijava.redis.entity.TUser;

import java.util.List;

/**
 * @author chenhx
 * @version UserService.java, v 0.1 2018-08-07 上午 10:48
 */
public interface UserService {
    /**
     * 查询所有权限
     *
     * @return
     */
    List<TPermission> selectAll();

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    TUser selectUserByUserName(String username);

    /**
     * 通过用户名查询角色名
     *
     * @param username
     * @return
     */
    String selectRoleNameByUserName(String username);

    /**
     * 根据角色名查询权限
     *
     * @param roleName
     * @return
     */
    List<String> selectPermissionsByRoleName(String roleName);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    int userRegister(TUser user);

}