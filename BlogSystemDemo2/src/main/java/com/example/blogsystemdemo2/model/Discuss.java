package com.example.blogsystemdemo2.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: YT
 * @Description: 评论
 * @DateTime: 2022/6/17 - 19:06
 */
@Data
public class Discuss {
    private int discussId;
    private int blogId;
    private int userId;
    private String username;
    private Timestamp postTime;
    private String content;
    private int isYourDiscuss;
}
