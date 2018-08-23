# spring-boot-shiro

# 开发说明

## spring-boot-shiro-hello-world
springboot集成shiro的入门项目  
使用注解配置拦截以及使用ShiroFilterFactoryBean进行配置拦截器  
使用静态配置权限，异常跳转等  

## spring-boot-shiro-mysql
springboot集成shiro，使用mysql数据库，动态从数据库读取权限配置  
并且更新数据库的权限表，不用重启项目即可进行更新shiro权限过滤器  
前端页面使用freemarker引擎模板渲染，根据权限动态显示菜单  
全局异常的拦截处理  
使用Shiro进行盐的获取以及密码进行MD5加密存储  
generator自定义注释输出表注释和字段注释  

## spring-boot-shiro-redis
本项目主要是集成redis缓存  
druid 监控配置配置  
配置了记住我的功能，其实就是存储到客户端的Cookie    


# 参与贡献
陈浩翔  

