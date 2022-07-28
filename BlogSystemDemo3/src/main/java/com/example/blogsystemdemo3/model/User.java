package com.example.blogsystemdemo3.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: 用户全部信息
 * @DateTime: 2022/7/27$ - 20:54
 */
@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private int number;
    private String email;
    private String github;

}
