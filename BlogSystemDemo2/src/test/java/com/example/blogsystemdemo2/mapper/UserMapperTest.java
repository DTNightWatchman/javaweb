package com.example.blogsystemdemo2.mapper;

import com.example.blogsystemdemo2.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/1$ - 21:46
 */

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void addNewUser() {
        String username = "admin1";
        String password = "password1";
        int flag = userMapper.addNewUser(username,password);
        System.out.println(flag);
    }

    @Test
    void findUserByName() {
        System.out.println(userMapper.findUserByName("YT1"));
    }

    @Test
    void findUserByNameAndPassword() {
        System.out.println(userMapper.findUserByNameAndPassword("YT","123"));
    }

    @Test
    void findUserById() {
        System.out.println(userMapper.findUserById(1));
    }

    @Test
    void updateNumber() {
        System.out.println(userMapper.updateNumber(25));
    }

    @Test
    void decNumber() {
        System.out.println(userMapper.decNumber(25));
    }

    @Test
    void getOwnerBlogList() {
        User user = userMapper.getOwnerBlogList(25);
        System.out.println(user);
    }
}