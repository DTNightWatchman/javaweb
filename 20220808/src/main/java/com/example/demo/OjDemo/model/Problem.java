package com.example.demo.OjDemo.model;

import lombok.Data;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-09
 * Time:15:31
 */
@Data
public class Problem {
    // id
    private int id;

    // 题目名字
    private String title;

    // 题目难度
    private String level;

    // 题目描述
    private String description;

    // 模板代码
    private String templateCode;

    // 测试代码，请求题目的时候不要返回
    private String testCode;
}
