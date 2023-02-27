package com.example.blogsystemdemo3.searcher.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: 用来存储查询结果的权重
 * @DateTime: 2022/7/29$ - 17:40
 */
@Data
public class Weight {
    private int id;

    // 表示相关性
    private int weight;
}
