package com.example.demo_java_oj.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: 编译返回结果
 * @DateTime: 2022/7/25$ - 16:56
 */
@Data
public class Answer {
    // 响应错误码，为0表示编译成功，为1表示编译错误，为2表示运行错误，为-1表示其他错误
    private int error;

    // 返回错误的原因，包括编译错误或者运行错误
    private String reason;

    // 标准输出
    private String stdout;

    // 标准错误
    private String stderr;
}
