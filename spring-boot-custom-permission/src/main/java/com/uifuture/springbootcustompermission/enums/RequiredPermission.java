/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.springbootcustompermission.enums;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 与拦截器结合使用 验证权限
 *
 * @author chenhx
 * @version RequiredPermission.java, v 0.1 2018-09-11 上午 11:43
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredPermission {
    String value();
}