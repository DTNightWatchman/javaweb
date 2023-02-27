package com.example.demo_blog_management.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/4$ - 10:04
 */
@Data
public class BlogDesc {
    private int blogId;
    private String title;
    private int userId;
    private Timestamp postTime;
    private int status;
}
