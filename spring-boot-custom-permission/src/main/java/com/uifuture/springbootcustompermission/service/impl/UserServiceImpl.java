/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.springbootcustompermission.service.impl;

import com.uifuture.springbootcustompermission.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenhx
 * @version UserServiceImpl.java, v 0.1 2018-09-11 上午 11:56
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Set<String> selectPermissionByRoleId(Integer roleId) {
        Set<String> stringSet = new HashSet<>();
        switch (roleId) {
            case 1:
                stringSet.add("product.delete");
                stringSet.add("product.edit");
                stringSet.add("product.list");
                stringSet.add("product");
                break;
            case 2:
                stringSet.add("product.list");
                break;
            default:
                break;
        }
        return stringSet;
    }
}