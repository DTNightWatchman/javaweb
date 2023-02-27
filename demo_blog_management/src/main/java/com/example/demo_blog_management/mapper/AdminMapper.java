package com.example.demo_blog_management.mapper;

import com.example.demo_blog_management.model.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/5$ - 21:10
 */
@Mapper
public interface AdminMapper {
    public Admin ifLogin(String username, String password);
}
