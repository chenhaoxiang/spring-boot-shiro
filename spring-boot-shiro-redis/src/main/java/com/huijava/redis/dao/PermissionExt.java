package com.huijava.redis.dao;

import com.huijava.redis.entity.TPermission;

import java.util.List;

public interface PermissionExt extends Permission {
    /**
     * 查询所有的权限
     *
     * @return
     */
    List<TPermission> selectAll();
}