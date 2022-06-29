package com.example.blogsystemdemo2.service;

import com.example.blogsystemdemo2.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jws.soap.SOAPBinding;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/11$ - 11:20
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void findUserByBlogId() {
        User user = userService.findUserByBlogId(1);
        System.out.println(user);
    }
}