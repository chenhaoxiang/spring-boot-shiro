package com.huijava.entity;

import java.io.Serializable;

/**
 * table name: t_user
 *
 * @author chenhaoxiang 2018-08-17
 */
public class TUser implements Serializable {
    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
    /**
     * field name : t_user.id
     */
    private Integer id;
    /**
     * 用户名
     * field name : t_user.username
     */
    private String username;
    /**
     * 密码
     * field name : t_user.password
     */
    private String password;
    /**
     * 角色id
     * field name : t_user.role_id
     */
    private Integer roleId;
    /**
     * 盐
     * field name : t_user.salt
     */
    private String salt;

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
     * 用户名
     *
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 密码
     *
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
     * 盐
     *
     * @return salt 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", roleId=").append(roleId);
        sb.append(", salt=").append(salt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}