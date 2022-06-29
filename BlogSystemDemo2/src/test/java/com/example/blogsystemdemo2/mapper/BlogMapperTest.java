package com.example.blogsystemdemo2.mapper;

import com.example.blogsystemdemo2.model.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/1$ - 20:27
 */

@SpringBootTest
class BlogMapperTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void getBlogById() {
        System.out.println(blogMapper.getBlogById(2));
    }

    @Test
    void insertBlog() {

    }

    @Test
    void insertBlog2() {
        Blog blog = new Blog();
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        blog.setTitle("title6");
        blog.setContent("content6");
        blog.setUserId(1);
        int t = blogMapper.insertBlog2(blog);
        System.out.println(blog.getBlogId());
    }

    @Test
    void getAllBlog() {
        List<Blog> list = blogMapper.getAllBlog();
        for (Blog blog: list) {
            System.out.println(blog);
        }
    }

    @Test
    void deleteBlogById() {
        int t = blogMapper.deleteBlogById(2);
        System.out.println(t);
    }
}