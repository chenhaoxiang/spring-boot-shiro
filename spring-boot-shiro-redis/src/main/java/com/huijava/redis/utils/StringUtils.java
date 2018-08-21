/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;

/**
 * @author chenhx
 * @version StringUtils.java, v 0.1 2018-08-07 下午 5:39
 */
public class StringUtils {
    /**
     * 使用shiro进行MD5加密
     *
     * @param hashIterations
     * @param password
     * @param salt
     * @return
     */
    public static String getPasswordMD5(int hashIterations, String password, String salt) {
        return new SimpleHash("MD5", password, salt, hashIterations).toString();
    }

    public static String getSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}