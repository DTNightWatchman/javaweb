package com.example.demo_blog_management.service;

import com.example.demo_blog_management.mapper.BlogMapper;
import com.example.demo_blog_management.mapper.DiscussMapper;
import com.example.demo_blog_management.model.Blog;
import com.example.demo_blog_management.model.BlogDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/4$ - 11:13
 */
@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private DiscussMapper discussMapper;

    public List<BlogDesc> getBlogDescs() {
        return blogMapper.getBlogDesc();
    }

    public int deleteBlogDesc(int blogId) {
        int ret = blogMapper.deleteBlogDesc(blogId);
        return  ret;
    }

    public int recoverBlog(int blogId) {
        int ret = blogMapper.recoverBlog(blogId);
        return ret;
    }

    @Transactional(timeout = 5)
    public int deleteBlog(int blogId) {
        int ret1 = blogMapper.deleteBlog(blogId);
        int ret2 = blogMapper.deleteBlogContent(blogId);
        discussMapper.deleteDiscussByBlogId(blogId);
        return ret1 & ret2;
    }

    public Blog getBlogById(int blogId) {
        Blog blog = blogMapper.getBlogById(blogId);
        return blog;
    }

    @Transactional(timeout = 5)
    public int updateBlog(int blogId,String title, String content) {
        int ret1 = blogMapper.updateBlogContent(blogId,content);
        String str = content.replaceAll("#",content);
        str = content.replaceAll("`",str);
        str.replaceAll("-",str);
        str.replaceAll("\\\\",str);
        int ret2 = 0;
        if (str.length() > 100) {
            ret2 = blogMapper.updateBlog(blogId,title,str.substring(0,100));
        } else {
            ret2 = blogMapper.updateBlog(blogId,title,str);
        }
        return ret1 & ret2;
    }

}
