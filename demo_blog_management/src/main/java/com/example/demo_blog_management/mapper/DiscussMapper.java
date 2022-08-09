package com.example.demo_blog_management.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/4$ - 17:59
 */
@Mapper
public interface DiscussMapper {
    public int deleteDiscussByBlogId(int blogId);

}
