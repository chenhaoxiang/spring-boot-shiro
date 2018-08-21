package com.huijava.redis.dao;

import java.util.List;

public interface RPermissionRoleExt extends RPermissionRole {
    /**
     * 通过角色名查询权限名
     *
     * @param roleName
     * @return
     */
    List<String> selectNameByRoleName(String roleName);
}