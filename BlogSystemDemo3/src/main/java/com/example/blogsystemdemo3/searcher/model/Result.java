package com.example.blogsystemdemo3.searcher.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: 返回结果
 * @DateTime: 2022/7/29$ - 17:38
 */

@Data
public class Result {
    private String title;
    private String url;

    // 正文的一段摘要，包含关键字的
    private String desc;
}
