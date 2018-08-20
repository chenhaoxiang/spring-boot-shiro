/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.config;

import com.huijava.entity.TPermission;
import com.huijava.server.UserService;
import com.huijava.shiro.CustomRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
    /**
     * 自定义身份认证
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     *
     * @return
     */
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }


    /**
     * 注入 securityManager
     * DefaultWebSecurityManager
     *
     * @param customRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
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


    /**
     * 动态设置权限拦截器
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

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<String> anonList = shiroFilterConfig.getAnon();
        for (String anon : anonList) {
            filterChainDefinitionMap.put(anon,
                    "anon");
        }
        //开放接口
//        filterChainDefinitionMap.put("/login", "anon");
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/index", "anon");
//        filterChainDefinitionMap.put("/", "anon");

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

}