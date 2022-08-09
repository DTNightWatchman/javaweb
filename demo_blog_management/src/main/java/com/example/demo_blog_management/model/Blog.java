package com.example.demo_blog_management.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: YT
 * @Description: 博客详情
 * @DateTime: 2022/8/5$ - 12:02
 */
@Data
public class Blog {
    private int blogId;
    private String title;
    private String content;
}
