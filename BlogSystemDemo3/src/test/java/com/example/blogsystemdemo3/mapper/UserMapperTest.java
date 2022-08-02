package com.example.blogsystemdemo3.mapper;

import com.example.blogsystemdemo3.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/27$ - 21:20
 */
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    void ifCouldLogin() {
        User user = userMapper.ifCouldLogin("admin",null,"admin");
        System.out.println(user);
    }

    @Test
    void incNumber() {
        int ret = userMapper.incNumber(2);
        System.out.println(ret);
    }


}