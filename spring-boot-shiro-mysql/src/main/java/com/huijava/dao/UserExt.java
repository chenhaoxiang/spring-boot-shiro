package com.huijava.dao;

import com.huijava.entity.TUser;

public interface UserExt extends User {
    /**
     * 根据用户名查询角色名
     *
     * @param username
     * @return
     */
    String selectRoleNameByUserName(String username);

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    TUser selectByUserName(String username);

}