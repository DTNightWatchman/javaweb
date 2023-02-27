package com.example.blogsystemdemo3.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: session for user
 * @DateTime: 2022/7/27$ - 23:11
 */
@Data
public class UserSession {
    private int userId;
    private String username;

    private int number;
    private String email;
    private String github;
}
