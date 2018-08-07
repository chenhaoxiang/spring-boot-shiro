/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.server.impl;

import com.huijava.constant.IntegerConstants;
import com.huijava.dao.TPermissionMapper;
import com.huijava.dao.TUserMapper;
import com.huijava.entity.TUser;
import com.huijava.server.UserService;
import com.huijava.utils.StringUtils;
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
    private TUserMapper tUserMapper;
    @Autowired
    private TPermissionMapper tPermissionMapper;

    @Override
    public TUser selectUserByUserName(String username) {
        return tUserMapper.selectByUserName(username);
    }

    @Override
    public String selectRoleNameByUserName(String username) {
        return tUserMapper.selectRoleNameByUserName(username);
    }

    @Override
    public List<String> selectPermissionsByRoleName(String roleName) {
        return tPermissionMapper.selectNameByRoleName(roleName);
    }

    @Override
    public int userRegister(TUser user) {
        user.setSalt(StringUtils.getSalt());
        user.setPassword(StringUtils.getPasswordMD5(IntegerConstants.HASH_ITERATIONS, user.getPassword(), user.getSalt()));
        return tUserMapper.insertSelective(user);
    }
}