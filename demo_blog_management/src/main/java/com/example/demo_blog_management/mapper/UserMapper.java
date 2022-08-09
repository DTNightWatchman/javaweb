package com.example.demo_blog_management.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/4$ - 10:11
 */
@Mapper
public interface UserMapper {
    public String getUsername(int userId);

    public String ifLogin(String username,String password);
}
