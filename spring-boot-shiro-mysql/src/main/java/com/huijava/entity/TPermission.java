package com.huijava.entity;

import java.io.Serializable;

/**
 * 权限表
 * table name: t_permission
 *
 * @author chenhaoxiang 2018-08-07
 */
public class TPermission implements Serializable {
    /**
     * t_permission
     */
    private static final long serialVersionUID = 1L;
    /**
     * field name : t_permission.id
     */
    private Integer id;
    /**
     * 权限名
     * field name : t_permission.name
     */
    private String name;
    /**
     * 角色id
     * field name : t_permission.role_id
     */
    private Integer roleId;
    /**
     * 权限说明
     * field name : t_permission.comment
     */
    private String comment;

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
     * 权限名
     *
     * @return name 权限名
     */
    public String getName() {
        return name;
    }

    /**
     * 权限名
     *
     * @param name 权限名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    /**
     * 权限说明
     *
     * @return comment 权限说明
     */
    public String getComment() {
        return comment;
    }

    /**
     * 权限说明
     *
     * @param comment 权限说明
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", roleId=").append(roleId);
        sb.append(", comment=").append(comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}