package com.example.blogsystemdemo2.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/1 - 21:38
 */

@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private int number;

    private List<Blog> blogList;
}
