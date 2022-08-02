package com.example.blogsystemdemo3.service;

import com.example.blogsystemdemo3.exception.UpdateErrorException;
import com.example.blogsystemdemo3.mapper.BlogMapper;
import com.example.blogsystemdemo3.mapper.UserMapper;
import com.example.blogsystemdemo3.model.Blog;
import com.example.blogsystemdemo3.model.Discuss;
import com.example.blogsystemdemo3.model.UserSession;
import com.example.blogsystemdemo3.searcher.Index;
import com.example.blogsystemdemo3.searcher.model.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: YT
 * @Description: 博客service
 * @DateTime: 2022/7/27$ - 23:30
 */
@Service
public class BlogService {



    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(timeout = 5)
    public int addBlog(Blog blog) {
        String str = null;
        if (blog.getContent().length() > 100){
            str = blog.getContent().substring(0,100);
        } else {
            str = blog.getContent();
        }
        str.replaceAll("\\*\\*","");
        str.replaceAll("```(.*)\n","");
        StringBuilder desc = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '>' && str.charAt(i) != '#' && str.charAt(i) != '`') {
                desc.append(str.charAt(i));
            }
        }
        blog.setBlogDesc(desc.toString());
        int ret1 = blogMapper.insertBlogDesc(blog);
        int ret2 = blogMapper.insertBlogContent(blog.getBlogId(),blog.getContent());
        int ret3 = userMapper.incNumber(blog.getUserId());
        Index index = new Index();
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setBlogId(blog.getBlogId());
        blogInfo.setContent(blog.getContent());
        blogInfo.setUrl("blogDetail.html?blogId=" + blogInfo.getBlogId());
        blogInfo.setTitle(blog.getTitle());
        index.addBlog(blogInfo);
        return ret1 & ret2 & ret3;
    }

    public List<Blog> getAllBlogDesc() {
        List<Blog> blogs = blogMapper.getAllBlogDesc();
        return blogs;
    }

    public Blog getBlogById(int blogId) {
        Blog blog = blogMapper.getBlogById(blogId);
        return blog;
    }

    public int incLikes(int blogId) {
        int ret = blogMapper.incLikes(blogId);
        int res = blogMapper.getLikes(blogId);
        return res;
    }

    public int getLikes(int blogId) {
        return blogMapper.getLikes(blogId);
    }

    @Transactional(timeout = 5,rollbackFor = UpdateErrorException.class)
    public int updateBlog(String content,String title,int blogId) {
        String str = null;
        if (content.length() > 100){
            str = content.substring(0,100);
        } else {
            str = content;
        }
        str.replaceAll("\\*\\*","");
        str.replaceAll("```(.*)\n","");
        StringBuilder desc = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '>' && str.charAt(i) != '#' && str.charAt(i) != '`') {
                desc.append(str.charAt(i));
            }
        }
        int ret1 = blogMapper.updateBlogDesc(desc.toString(),title,blogId);
        if (ret1 != 1) {
            try {
                throw new UpdateErrorException();
            } catch (Exception e) {
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        int ret2 = blogMapper.updateBlogContent(content,blogId);
        return ret2;
    }

    @Transactional(timeout = 5)
    public int deleteBlog(int blogId,int userId) {
        int ret1 = blogMapper.deleteBlog(blogId);
        int ret2 = userMapper.decNumber(userId);
//        int ret1 = blogMapper.deleteBlogDesc(blogId);
//        int ret2 = blogMapper.deleteBlogContent(blogId);
//        int ret3 = userMapper.decNumber(userId);
        return ret1 & ret2;
    }

    public int getUserIdByBlogId(int blogId) {
        return blogMapper.getUserIdByBlogId(blogId);
    }

    public List<Blog> getBlogsByUserId(int userId) {
        List<Blog> blogs = blogMapper.getBlogsByUserId(userId);
        return blogs;
    }


}
