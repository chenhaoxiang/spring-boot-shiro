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

import java.util.Set;

/**
 * @author chenhx
 * @version UserServiceImpl.java, v 0.1 2018-08-02 下午 5:24
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Set<String> selectRolesByUsername(String username) {
        return userDao.selectRolesByUsername(username);
    }

    @Override
    public Set<String> selectPermissionsByRole(String role) {
        return userDao.selectPermissionsByRole(role);
    }

    @Override
    public String selectPasswordByUsername(String username) {
        return userDao.selectPasswordByUsername(username);
    }

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
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(userDao.selectPasswordByUsername(username));
        return users;
    }
}