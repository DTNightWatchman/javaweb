package com.example.blogsystemdemo3.mapper;

import com.example.blogsystemdemo3.model.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/27$ - 23:45
 */
@SpringBootTest
class BlogMapperTest {
    @Autowired
    private BlogMapper blogMapper;

    @Test
    void insertBlogDesc() {
        Blog blog = new Blog();
        blog.setTitle("title1");
        blog.setBlogDesc("1111111111111");
        blog.setUserId(1);
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        int ret = blogMapper.insertBlogDesc(blog);
        System.out.println(blog.getBlogId());
//        blogMapper.insertBlogContent(1,blog.getContent());

    }

    @Test
    void insertBlogContent() {

    }

    @Test
    void getBlogById() {
        Blog blog = blogMapper.getBlogById(1);
        System.out.println(blog);
    }

    @Test
    void getUserIdByBlogId() {
        int ret = blogMapper.getUserIdByBlogId(18);
        System.out.println(ret);
    }
}