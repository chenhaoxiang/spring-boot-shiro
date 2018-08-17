package com.huijava.entity;

import java.io.Serializable;

/**
 * 角色权限关系表
 * table name: t_r_permission_role
 *
 * @author chenhaoxiang 2018-08-17
 */
public class TRPermissionRole implements Serializable {
    /**
     * t_r_permission_role
     */
    private static final long serialVersionUID = 1L;
    /**
     * field name : t_r_permission_role.id
     */
    private Integer id;
    /**
     * 权限id
     * field name : t_r_permission_role.permission_id
     */
    private Integer permissionId;
    /**
     * 角色id
     * field name : t_r_permission_role.role_id
     */
    private Integer roleId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 权限id
     *
     * @return permission_id 权限id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * 权限id
     *
     * @param permissionId 权限id
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 角色id
     *
     * @return role_id 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}