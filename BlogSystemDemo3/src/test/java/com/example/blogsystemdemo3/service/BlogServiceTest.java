package com.example.blogsystemdemo3.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/28$ - 13:49
 */
@SpringBootTest
class BlogServiceTest {

    @Autowired
    private BlogService blogService;

    @Test
    void incLikes() {
        int ret = blogService.incLikes(1);
        System.out.println(ret);
    }
}