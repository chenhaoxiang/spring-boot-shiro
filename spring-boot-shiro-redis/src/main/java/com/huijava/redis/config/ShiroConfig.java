/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.config;

import com.huijava.redis.entity.TPermission;
import com.huijava.redis.server.UserService;
import com.huijava.redis.shiro.CustomRealm;
import com.huijava.redis.utils.PasswordUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro的配置类，进行配置自定义的Realm和拦截器规则
 *
 * @author chenhx
 * @version ShiroConfig.java, v 0.1 2018-08-03 下午 2:46
 */
@Configuration
public class ShiroConfig {

    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        return redisManager;
    }

    /**
     * 配置redis缓存管理器
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 这里需要设置成与PasswordEncrypter类相同的加密规则
     * <p>
     * 在doGetAuthenticationInfo认证登陆返回SimpleAuthenticationInfo时会使用hashedCredentialsMatcher
     * 把用户填入密码加密后生成散列码与数据库对应的散列码进行对比
     * <p>
     * HashedCredentialsMatcher会自动根据AuthenticationInfo的类型是否是SaltedAuthenticationInfo来获取credentialsSalt盐
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法, 与注册时使用的散列算法相同
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordUtils.getAlgorithmName());
        // 散列次数, 与注册时使用的散列册数相同
        hashedCredentialsMatcher.setHashIterations(PasswordUtils.getHashIterations());
        // 生成16进制, 与注册时的生成格式相同
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义的认证类，继承子AuthorizingRealm，负责用户的认证和权限处理
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     *
     * @return
     */
    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        CustomRealm customRealm = new CustomRealm();
        // 设置加密算法
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return customRealm;
    }


    /**
     * 安全管理器 将realm加入securityManager
     * DefaultWebSecurityManager
     *
     * @param customRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(customRealm);
        //设置缓存
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }


    /**
     * shiro filter 工厂类
     * 1.定义ShiroFilterFactoryBean
     * 2.设置SecurityManager
     * 3.配置拦截器
     * 4.返回定义ShiroFilterFactoryBean
     *
     * @param securityManager
     * @param userService
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager, UserService userService, ShiroFilterConfig shiroFilterConfig) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面 登录页面
        if (shiroFilterConfig.getLoginUrl() != null && shiroFilterConfig.getLoginUrl().length() > 0) {
            shiroFilterFactoryBean.setLoginUrl(shiroFilterConfig.getLoginUrl());
        } else {
            shiroFilterFactoryBean.setLoginUrl("/login");
        }
        // 登录成功后要跳转的链接
        if (shiroFilterConfig.getSuccessUrl() != null && shiroFilterConfig.getSuccessUrl().length() > 0) {
            shiroFilterFactoryBean.setSuccessUrl(shiroFilterConfig.getSuccessUrl());
        } else {
            shiroFilterFactoryBean.setSuccessUrl("/index");
        }
        // 未授权界面;
        if (shiroFilterConfig.getUnauthorizedUrl() != null && shiroFilterConfig.getUnauthorizedUrl().length() > 0) {
            shiroFilterFactoryBean.setSuccessUrl(shiroFilterConfig.getUnauthorizedUrl());
        } else {
            shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        }

        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<String> anonList = shiroFilterConfig.getAnon();
        for (String anon : anonList) {
            //开放接口
            filterChainDefinitionMap.put(anon,
                    "anon");
        }
        //从数据库获取
        List<TPermission> tPermissionList = userService.selectAll();
        for (TPermission tPermission : tPermissionList) {
            filterChainDefinitionMap.put(tPermission.getUrl(),
                    "perms[" + tPermission.getName() + "]");
        }

        //其余接口一律拦截
        if (shiroFilterConfig.isAllUrlAuthentication()) {
            //没有配置该句，表示其他的页面全部可以访问
            filterChainDefinitionMap.put("/**", "authc");
        }

        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);

        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }


    /**
     * LifecycleBeanPostProcessor将Initializable和Destroyable的实现类统一在其内部自动分别调用了
     * Initializable.init()和Destroyable.destroy()方法，从而达到管理shiro bean生命周期的目的。
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}