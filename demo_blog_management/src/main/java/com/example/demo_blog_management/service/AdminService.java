package com.example.demo_blog_management.service;

import com.example.demo_blog_management.mapper.AdminMapper;
import com.example.demo_blog_management.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/5$ - 21:12
 */
@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin ifLogin(String username, String password) {
        Admin admin = adminMapper.ifLogin(username,password);
        return admin;
    }
}
