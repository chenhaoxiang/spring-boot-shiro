package com.huijava.redis.entity;

import java.io.Serializable;

/**
 * 角色表
 * table name: t_role
 *
 * @author chenhaoxiang 2018-08-17
 */
public class TRole implements Serializable {
    /**
     * t_role
     */
    private static final long serialVersionUID = 1L;
    /**
     * field name : t_role.id
     */
    private Integer id;
    /**
     * 角色名
     * field name : t_role.name
     */
    private String name;
    /**
     * field name : t_role.comment
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
     * 角色名
     *
     * @return name 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名
     *
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
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
        sb.append(", comment=").append(comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}