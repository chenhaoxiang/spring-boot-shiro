/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.server;

import java.util.Map;

/**
 * 动态更新权限
 *
 * @author chenhx
 * @version ShiroService.java, v 0.1 2018-08-20 上午 10:29
 */
public interface ShiroService {
    /**
     * 初始化权限
     *
     * @return
     */
    Map<String, String> loadFilterChainDefinitions();

    /**
     * 重新加载权限
     */
    void updatePermission();
}
