package com.example.blogsystemdemo3.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: YT
 * @Description: discuss
 * @DateTime: 2022/7/29$ - 14:08
 */
@Data
public class Discuss {
    private int discussId;
    private int blogId;
    private int userId;
    private String username;
    private Timestamp postTime;
    private String content;

}
