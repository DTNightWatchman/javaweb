package com.example.blogsystemdemo2.service;

import com.example.blogsystemdemo2.mapper.BlogMapper;
import com.example.blogsystemdemo2.mapper.UserMapper;
import com.example.blogsystemdemo2.model.Blog;
import com.example.blogsystemdemo2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YT
 * @Description: 博客管理
 * @DateTime: 2022/6/3$ - 11:57
 */
@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserMapper userMapper;

    public int insertBlog(Blog blog) {
        int result = blogMapper.insertBlog2(blog);
        int number = userMapper.updateNumber(blog.getUserId());
        if (number == 1 &&  result == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public List<Blog> getAllBlogs() {
        List<Blog> list = blogMapper.getAllBlog();
        for (Blog blog: list) {
            String content1 = blog.getContent();
            //System.out.println(content1);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < content1.length(); i++) {
                if (content1.charAt(i) != '*' && content1.charAt(i) != '#' &&
                        content1.charAt(i) != '~' && content1.charAt(i) != '>') {
                    stringBuilder.append(content1.charAt(i));
                }
            }
            //System.out.println(stringBuilder.toString());
            blog.setContent(stringBuilder.toString());
        }
        return list;
    }

    public Blog getBlogById(int blogId) {
        Blog blog = blogMapper.getBlogById(blogId);
        return blog;
    }

    public int deleteBlogById(int blogId) {
        Blog blog = blogMapper.getBlogById(blogId);
        User user = userMapper.findUserById(blog.getUserId());
        int ret = userMapper.decNumber(user.getUserId());
        int t = blogMapper.deleteBlogById(blogId);

        if (t == 1 && ret == 1) {
            return t;
        } else {
            return 0;
        }
    }
}
