package com.example.blogsystemdemo3.searcher.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: 文章信息
 * @DateTime: 2022/7/29$ - 17:36
 */
@Data
public class BlogInfo {
    private int id;
    private int blogId;
    private String title;
    private String url;
    private String content;
}
