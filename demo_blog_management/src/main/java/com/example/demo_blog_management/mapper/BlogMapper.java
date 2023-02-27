package com.example.demo_blog_management.mapper;

import com.example.demo_blog_management.model.Blog;
import com.example.demo_blog_management.model.BlogDesc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/4$ - 10:07
 */
@Mapper
public interface BlogMapper {
    public List<BlogDesc> getBlogDesc();

    public int deleteBlog(int blogId);

    public int deleteBlogDesc(int blogId);

    public int recoverBlog(int blogId);

    public Blog getBlogById(int blogId);

    public int deleteBlogContent(int blogId);

    public int updateBlog(int blogId,String title,String blogDesc);

    public int updateBlogContent(int blogId,String content);
}
