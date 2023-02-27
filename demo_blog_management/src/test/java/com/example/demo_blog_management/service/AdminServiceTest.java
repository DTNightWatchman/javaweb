package com.example.demo_blog_management.service;

import com.example.demo_blog_management.model.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/5$ - 21:13
 */
@SpringBootTest
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    void ifLogin() {
        Admin admin = adminService.ifLogin("admin","admin");
        System.out.println(admin);
    }
}