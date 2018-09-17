package com.huijava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huijava.dao")
public class SpringBootShiroMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShiroMysqlApplication.class, args);
    }

}
