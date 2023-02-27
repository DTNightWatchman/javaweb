package com.example.blogsystemdemo3.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: 题目
 * @DateTime: 2022/7/25$ - 20:20
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
