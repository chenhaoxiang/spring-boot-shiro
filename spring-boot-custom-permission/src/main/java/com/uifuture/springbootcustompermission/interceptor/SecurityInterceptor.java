/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.springbootcustompermission.interceptor;

import com.uifuture.springbootcustompermission.constants.PermissionConstants;
import com.uifuture.springbootcustompermission.entity.User;
import com.uifuture.springbootcustompermission.enums.RequiredPermission;
import com.uifuture.springbootcustompermission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 权限拦截器
 *
 * @author chenhx
 * @version SecurityInterceptor.java, v 0.1 2018-09-11 上午 11:39
 */
public class SecurityInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证权限
        if (this.hasPermission(request, handler)) {
            return true;
        }
        // 如果没有权限 则抛403异常 springboot会处理，跳转到 /error/403 页面
//        response.sendError(HttpStatus.FORBIDDEN.value(), "无权限");
        throw new RuntimeException("没有权限");
    }

    /**
     * 是否有权限
     *
     * @param request
     * @param handler
     * @return
     */
    private boolean hasPermission(HttpServletRequest request, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }
            // 如果标记了注解，则判断权限
            if (requiredPermission != null && !StringUtils.isEmpty(requiredPermission.value())) {
                //获取当前用户
                User user = (User) request.getSession().getAttribute(PermissionConstants.LOGIN_USER_SESSION);
                if (user == null) {
                    throw new RuntimeException("用户尚未登录");
                }
                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
                Set<String> permissionSet = userService.selectPermissionByRoleId(user.getRoleId());
                if (CollectionUtils.isEmpty(permissionSet)) {
                    return false;
                }
                return permissionSet.contains(requiredPermission.value());
            }
        }
        return true;
    }


    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}