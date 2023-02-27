package com.example.demo_blog_management.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: 题目
 * @DateTime: 2022/8/3$ - 14:19
 */
@Data
public class Problem {
    private int id;
    private String title;
    private String level;
    private String description;
    private String templateCode;
    private String testCode;
}
