/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.server.impl;

import com.huijava.dao.PermissionExt;
import com.huijava.dao.RPermissionRoleExt;
import com.huijava.dao.UserExt;
import com.huijava.entity.TPermission;
import com.huijava.entity.TUser;
import com.huijava.server.UserService;
import com.huijava.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhx
 * @version UserServiceImpl.java, v 0.1 2018-08-07 上午 10:58
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserExt userExt;

    @Autowired
    private PermissionExt permissionExt;
    @Autowired
    private RPermissionRoleExt rPermissionRoleExt;


    @Override
    public List<TPermission> selectAll() {
        return permissionExt.selectAll();
    }

    @Override
    public TUser selectUserByUserName(String username) {
        return userExt.selectByUserName(username);
    }

    @Override
    public String selectRoleNameByUserName(String username) {
        return userExt.selectRoleNameByUserName(username);
    }

    @Override
    public List<String> selectPermissionsByRoleName(String roleName) {
        return rPermissionRoleExt.selectNameByRoleName(roleName);
    }

    @Override
    public int userRegister(TUser user) {
        //设置盐和密码
        PasswordUtils.encryptPassword(user);
        return userExt.insertSelective(user);
    }
}