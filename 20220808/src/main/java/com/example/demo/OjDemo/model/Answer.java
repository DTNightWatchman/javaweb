package com.example.demo.OjDemo.model;

import lombok.Data;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-10
 * Time:14:27
 */
@Data
public class Answer {
    // 表示错误 0表示运行成功， 1表示编译失败  2表示运行错误
    private int error;

    // 编译错误原因
    private String reason;

    // 运行结果
    private String stdout;

    // 标准运行错误结果
    private String stderr;
}
