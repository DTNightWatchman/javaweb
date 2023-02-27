package com.example.demo_blog_management.service;

import com.example.demo_blog_management.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YT
 * @Description: 用户
 * @DateTime: 2022/8/4$ - 11:06
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String getUsername(int userId) {
        return userMapper.getUsername(userId);
    }
}
