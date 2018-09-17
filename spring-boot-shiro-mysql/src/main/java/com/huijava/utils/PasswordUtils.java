/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.utils;

import com.huijava.entity.TUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码工具类
 *
 * @author chenhx
 * @version PasswordUtils.java, v 0.1 2018-08-20 下午 3:06
 */
public class PasswordUtils {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    /**
     * 散列算法
     */
    private static String ALGORITHM_NAME = "MD5";
    /**
     * 散列次数
     */
    private static int HASH_ITERATIONS = 1024;

    public static <T extends TUser> void encryptPassword(T t) {
        // 随机生成盐
        t.setSalt(randomNumberGenerator.nextBytes().toHex());
        // 加密  生成密码散列值使用了Shiro提供的SimpleHash类，其内部使用了Java的MessageDigest实现
        String encryptPwd = new SimpleHash(ALGORITHM_NAME, t.getPassword(),
                ByteSource.Util.bytes(t.getSalt()), HASH_ITERATIONS).toHex();
        t.setPassword(encryptPwd);
    }

    /**
     * Getter method for property <tt>ALGORITHM_NAME</tt>.
     *
     * @return property value of ALGORITHM_NAME
     */
    public static String getAlgorithmName() {
        return ALGORITHM_NAME;
    }

    /**
     * Getter method for property <tt>HASH_ITERATIONS</tt>.
     *
     * @return property value of HASH_ITERATIONS
     */
    public static int getHashIterations() {
        return HASH_ITERATIONS;
    }
}