package com.example.blogsystemdemo3.mapper;

import com.example.blogsystemdemo3.model.Blog;
import com.example.blogsystemdemo3.service.BlogService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/27$ - 23:35
 */
@Mapper
public interface BlogMapper {
    public int insertBlogDesc(Blog blog);

    public int insertBlogContent(int blogId, String content);

    public List<Blog> getAllBlogDesc();

    public Blog getBlogById(int blogId);

    public int incLikes(int blogId);

    public int getLikes(int blogId);

    public int updateBlogDesc(String blogDesc, String title,int blogId);

    public int updateBlogContent(String content,int blogId);

    public int deleteBlogDesc(int blogId);

    public int deleteBlogContent(int blogId);

    // todo 做报错处理
    public int getUserIdByBlogId(int blogId);

    public List<Blog> getBlogsByUserId(int userId);
}
