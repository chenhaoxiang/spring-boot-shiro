package com.huijava.dao;

import com.huijava.entity.TPermission;

import java.util.List;

public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    List<String> selectTPermissionNameByRoleId(Integer roleId);

    /**
     * 通过角色名查询权限名
     *
     * @param roleName
     * @return
     */
    List<String> selectNameByRoleName(String roleName);
}