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

# Shiro介绍  

Shiro是什么  
Apache Shiro是一个开源的安全框架。可以处理身份验证，授权，会话管理，加密，缓存等  

Shrio的基本功能图：
![Shrio的基本功能图](http://blogimg.chenhaoxiang.cn/18-9-4/54415615.jpg)

最主要功能（也称为Shiro的四大基石）：  
1. Authentication：身份认证/登录，验证用户是不是拥有相应的身份；
2. Authorization：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；
3. Session Manager：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通JavaSE环境的，也可以是如Web环境的；
4. Cryptography：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；

辅助特性：
1. Web Support：Web支持，可以非常容易的集成到Web环境；
2. Caching：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率；
3. Concurrency：shiro支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；
4. Testing：提供测试支持；
5. Run As：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；
6. Remember Me：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。

一般情况下，我们在开发中常用的也就是Shiro的身份验证，授权和加密，另外Remember Me可能也会经常用到，缓存一般是集成Redis进行。   
主要也就是讲解该部分。对于一个好的框架，从外部来看应该具有非常简单易于使用的API，且API契约明确；从内部来看的话，其应该有一个可扩展的架构，即非常容易插入用户自定义实现，因为任何框架都不能满足所有需求。  

首先，我们从外部来看Shiro，也就是从应用程序的角度查看Shiro是如何完成工作的  
![外部来看Shiro](http://blogimg.chenhaoxiang.cn/18-9-4/24091330.jpg)

 从该图中，可以看出，我们应用代码直接进行交互的对象是Subject，也就是说Shiro中的对外API核心就是Subject。  
1. Subject：主体，代表了当前“用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是Subject，如网络爬虫，机器人等；即一个抽象概念；所有Subject都绑定到SecurityManager（安全管理器），与Subject的所有交互都会委托给SecurityManager；可以把Subject认为是一支笔；SecurityManager才是实际的持笔人；  
2. SecurityManager：安全管理器；即所有与安全有关的操作都会与SecurityManager交互；且它管理着所有Subject；可以看出它是Shiro的核心，它负责与Shiro的其他组件进行交互，你可以把它看成SpringMVC中的DispatcherServlet前端控制器；
3. Realm：领域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；可以把Realm看成DataSource，也就是安全数据源。领域可以配置一个或者多个。  

Realm本质上是一个特定情况的DAO，它封装了数据源的连接细节，并且根据需要，Shiro可以获取相关的数据，配置Shiro时，至少需要指定一个Realm，用于身份认证或者授权。所以SecurityManager可能配置多个Realm，但是至少需要配置一个。  

Shrio中也提供了一些开箱即用的Realm，可以连接到许多的安全数据源，比如LDAP（轻量目录访问协议），关系数据库（JDBC），文本配置源（INI文件或者属性文件等）。如果这些无法满足需求，可以插入自己的Realm实现自定义的数据库，一般情况下，使用文本和数据库即够用了。   

SecurityManager负责管理如何获取和使用Realm作为Subject实例的安全性和身份数据。也就是说对于我们而言，最简单的一个Shiro应用，实现身份验证和授权需要下面的几步：
1. 应用代码通过Subject来进行认证和授权，而Subject又委托给SecurityManager；
2. 我们需要给Shiro的SecurityManager注入Realm，从而让SecurityManager能得到合法的用户及其权限进行判断。  

Shiro不提供用户和权限，需要开发人员通过Realm自己进行注入。可以静态注入，也可以动态更新，后面在代码中会演示到   

接下来看下Shrio内部的核心架构图
![Shrio内部的核心架构图](http://blogimg.chenhaoxiang.cn/18-9-4/3048186.jpg)

1. Subject (org.apache.shiro.subject.Subject):主体。理解为和应用程序交互的安全性的"视图"
2. SecurityManager (org.apache.shiro.mgt.SecurityManager):安全管理器。Shiro的核心，类比于Spring MVC中的前端控制器DispatcherServlet.Shiro和应用程序的所有具体交互都会通过SecurityManager进行控制。管理所有的Subject，负责进行认证，授权，会话以及缓存的管理  
3. Authenticator (org.apache.shiro.authc.Authenticator):认证器。负责执行和响应用户进行身份验证（登录）的组件。可以自定义实现，实现认证策略即可，也就是什么情况下算用户成功认证了。  
4. Authentication Strategy (org.apache.shiro.authc.pam.AuthenticationStrategy):认证策略。如果配置了多个领域，AuthenticationStrategy会协调领域确认身份认证成功或者失败的条件。比如说，一个领域认证成功，其他领域全部认证失败，那么是认证成功还是失败？  
5. Authorizer (org.apache.shiro.authz.Authorizer):授权器。授权器是决定应用程序中用户访问控制的组件，是一种机制，用来决定用户是否有权限进行相应的操作。即控制用户能够访问应用程序中哪些功能。 
6. SessionManager (org.apache.shiro.session.mgt.SessionManager):会话管理。Session的概念和我们在Servlet中的概念一致。会话管理是用来管理Session的生命周期的。由于Shiro不仅仅只用在Web环境下，也可以在普通的JavaSE环境下使用。所以Shiro就抽象出自己的一个Session管理器来管理主体和应用之间交互的数据。如需要实现分布式，则使用Redis管理Session即可。
7. SessionDAO (org.apache.shiro.session.mgt.eis.SessionDAO):SessionDAO的存在是为了允许任何数据源用于持久化会话。DAO大家都用过，数据访问对象，用于会话的CRUD（持久化），比如我们想把Session保存到数据库，那么可以实现自己的SessionDAO，通过JDBC写到数据库；比如想把Session放到Redis中，可以实现自己的Redis SessionDAO；另外SessionDAO中可以使用Cache进行缓存，以提高性能；
8. CacheManager (org.apache.shiro.cache.CacheManager):缓存控制器。缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能。如果需要动态更新权限，如果实现了缓存，那么同时需要更新该组件。 
9. Cryptography (org.apache.shiro.crypto.*):密码模块。Shiro提供了一些常见的加密组件用于密码加密/解密。散列算法MD5，SHA等，base64等
10. Realms (org.apache.shiro.realm.Realm):可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；可以是JDBC实现，也可以是LDAP（轻量目录访问协议）实现，或者内存实现等等；由用户提供；注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的Realm；

# 使用Shiro  
Shiro能做什么官网：http://shiro.apache.org/reference.html  
未整合Spring以前，是需要在Web.xml中定义org.apache.shiro.web.servlet.ShiroFilter过滤器的  
Shiro的初始化工作在web.xml中设置监听器完成
<listener>   
 <listener-class>org.apache.shiro.web.env.EnvironmentLoaderListener</listener-class>
 </listener>
 
 和SpringBoot整合

# 参与贡献
陈浩翔   

