/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.springbootcustompermission.service;

import java.util.Set;

/**
 * @author chenhx
 * @version UserService.java, v 0.1 2018-09-11 上午 11:55
 */
public interface UserService {
    Set<String> selectPermissionByRoleId(Integer roleId);
}
