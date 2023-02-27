package com.example.blogsystemdemo3.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: YT
 * @Description: blog
 * @DateTime: 2022/7/27$ - 23:36
 */
@Data
public class Blog {
    private int blogId;
    private String title;
    private String blogDesc;
    private Timestamp postTime;
    private int userId;
    private String content;
    private int likes;
}
