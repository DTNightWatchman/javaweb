package com.example.blogsystemdemo2.mapper;

import com.example.blogsystemdemo2.model.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/1$ - 0:53
 */

@Mapper
public interface BlogMapper {
    public Blog getBlogById(int blogId);

    public int insertBlog(String title, String content, int userId);

    public int insertBlog2(Blog blog);

    public List<Blog> getAllBlog();

    public int deleteBlogById(int blogId);

}
