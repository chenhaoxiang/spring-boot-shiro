/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.helloworld.service.impl;

import com.huijava.helloworld.dao.UserDao;
import com.huijava.helloworld.entity.Users;
import com.huijava.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通过 @RequiresPermissions 等注解设置访问资源所需的权限 - 这样注解配置不灵活
 * @author chenhx
 * @version UserServiceImpl.java, v 0.1 2018-08-02 下午 5:24
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer addUser(Users users) {
        System.out.println("插入用户完成...");
        return 1;
    }

    @Override
    public Integer updateUser(Users users) {
        System.out.println("修改用户完成...");
        return 1;
    }

    @Override
    public Integer deleteUser(Users users) {
        System.out.println("删除用户完成....");
        return 1;
    }

    @Override
    public Users selectUser(String username) {
        System.out.println("selectUser...");
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(userDao.selectPasswordByUsername(username));
        System.out.println("查询出来的用户为:" + users);
        return users;
    }
}