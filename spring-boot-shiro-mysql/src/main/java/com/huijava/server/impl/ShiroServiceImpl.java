/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.server.impl;

import com.huijava.config.ShiroFilterConfig;
import com.huijava.entity.TPermission;
import com.huijava.server.ShiroService;
import com.huijava.server.UserService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新权限
 *
 * @author chenhx
 * @version ShiroServiceImpl.java, v 0.1 2018-08-20 上午 10:30
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private ShiroFilterConfig shiroFilterConfig;
    @Autowired
    private UserService userService;

    /**
     * 初始化权限
     *
     * @return
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
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
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    @Override
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new RuntimeException(
                        "get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                    .getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();
            //清空权限
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            //设置新的权限
            shiroFilterFactoryBean
                    .setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean
                    .getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                //防止空格
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                //创建新的权限控制
                manager.createChain(url, chainDefinition);
            }

            System.out.println("更新权限成功！！");
        }
    }
}