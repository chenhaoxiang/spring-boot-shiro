package com.huijava.entity;

import java.io.Serializable;

/**
 * 权限表
 * table name: t_permission
 *
 * @author chenhaoxiang 2018-08-17
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
     * 权限对应的访问路径，细分到每个路径
     * field name : t_permission.url
     */
    private String url;
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
     * 权限对应的访问路径，细分到每个路径
     *
     * @return url 权限对应的访问路径，细分到每个路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 权限对应的访问路径，细分到每个路径
     *
     * @param url 权限对应的访问路径，细分到每个路径
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
        sb.append(", url=").append(url);
        sb.append(", comment=").append(comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}