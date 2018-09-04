package com.huijava.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huijava.redis.dao")
public class SpringBootShiroRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShiroRedisApplication.class, args);
    }
}

