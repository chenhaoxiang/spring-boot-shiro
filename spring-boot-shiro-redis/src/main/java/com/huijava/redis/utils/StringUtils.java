/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
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


    /**
     * 随机生成AES秘钥
     *
     * @param i 生成的位数
     */
    public static String getAESKey(Integer i) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        //要生成多少位，只需要修改这里即可128, 192或256
        kg.init(i);
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        return byteToHexString(b);
    }

    /**
     * byte数组转化为16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }


}