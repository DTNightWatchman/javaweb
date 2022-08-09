package com.example.demo_blog_management.mapper;

import com.example.demo_blog_management.model.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/5$ - 13:31
 */
@SpringBootTest
class BlogMapperTest {


    @Autowired
    private BlogMapper blogMapper;
    @Test
    void getBlogById() {
        Blog blog = blogMapper.getBlogById(1);
        System.out.println(blog);
    }
}