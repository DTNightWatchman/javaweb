package com.example.blogsystemdemo2.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/1$ - 0:53
 */
@Data
public class Blog {
    private int blogId;
    private String title;
    private String content;
    private Timestamp postTime;
    private int userId;
}
