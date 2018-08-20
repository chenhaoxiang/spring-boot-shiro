/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.shiro;

import com.huijava.entity.TUser;
import com.huijava.server.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 * Realm 是控制认证和授权的核心部分，也是开发人员必须自己实现的部分。
 * 实现自定义 Realm 最快捷的方式是继承 AuthorizingRealm 类。
 *
 * @author chenhx
 * @version CustomRealm.java, v 0.1 2018-08-03 下午 2:40
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权
     * SimpleAuthorizationInfo进行角色的添加和权限的添加。
     * 每次请求都会执行，可以使用redis对shiro的用户信息进行缓存，不用每次都去执行
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String role = this.userService.selectRoleNameByUserName(username);
        authorizationInfo.addRole(role);
        List<String> permissions = this.userService.selectPermissionsByRoleName(role);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //通过username从数据库中查找 User对象
        //实际项目中，这里可以根据实际情况做Redis缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        TUser user = this.userService.selectUserByUserName(username);
        System.out.println("====>user=" + user);
        if (user == null) {
            //throw new UnknownAccountException();
            // 返回null，会使任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
            return null;
        }
        // 未进行加密
//        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        // 进行加密后的使用
        // Shiro会默认会使用HashedCredentialsMatcher中的方式（盐值已经在SimpleAuthenticationInfo中传入了）把用户输入的密码生成散列值与数据库的密码作比较，如果相同，则通过校验，否则抛出异常。
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }


}