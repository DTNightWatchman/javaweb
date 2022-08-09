package com.example.demo_blog_management.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/4$ - 14:20
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    void getUsername() {
        System.out.println(userService.getUsername(1));
    }
}