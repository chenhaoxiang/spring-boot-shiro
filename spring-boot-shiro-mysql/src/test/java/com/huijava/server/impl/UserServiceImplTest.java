package com.huijava.server.impl;

import com.huijava.entity.TUser;
import com.huijava.server.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void selectUserByUserName() {
        log.info("=====user={}", userService.selectUserByUserName("json"));
    }

    @Test
    public void selectRoleNameByUserName() {
        log.info(userService.selectRoleNameByUserName("user"));
    }

    @Test
    public void selectPermissionsByRoleName() {
    }

    /**
     * 用户注册
     */
    @Test
    public void userRegister() {
        TUser user = new TUser();
        user.setUsername("user2");
        user.setPassword("1234");
        user.setRoleId(2);
        System.out.println("===============" + userService.userRegister(user));
    }
}