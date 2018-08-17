package com.huijava.dao;

import com.huijava.entity.TRPermissionRole;

public interface RPermissionRole {
    int deleteByPrimaryKey(Integer id);

    int insert(TRPermissionRole record);

    int insertSelective(TRPermissionRole record);

    TRPermissionRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRPermissionRole record);

    int updateByPrimaryKey(TRPermissionRole record);
}