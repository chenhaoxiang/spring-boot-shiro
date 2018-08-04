/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.helloworld.service;

import com.huijava.helloworld.entity.Users;

/**
 * 用户Service层
 *
 * @author chenhx
 * @version UserService.java, v 0.1 2018-08-02 下午 5:12
 */
public interface UserService {
    /**
     * 增加用户
     *
     * @param users
     * @return
     */
    Integer addUser(Users users);

    /**
     * 修改用户
     *
     * @param users
     * @return
     */
    Integer updateUser(Users users);

    /**
     * 删除用户
     *
     * @param users
     * @return
     */
    Integer deleteUser(Users users);

    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    Users selectUser(String username);
}