/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.springbootcustompermission.entity;

import lombok.Data;

/**
 * @author chenhx
 * @version User.java, v 0.1 2018-09-11 上午 11:52
 */
@Data
public class User {
    private String username;
    private String password;
    private Integer roleId;
}