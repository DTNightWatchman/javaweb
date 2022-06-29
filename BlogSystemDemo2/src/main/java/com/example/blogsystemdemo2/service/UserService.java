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
 * @Description:
 * @DateTime: 2022/6/1$ - 21:36
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogMapper blogMapper;

    public int addNewUser(String username, String password) {
        User user = userMapper.findUserByName(username);
        System.out.println(user);
        if (user != null) {
            return 0;
        }
        int flag = userMapper.addNewUser(username, password);
        return flag;
    }

    public User findUser(String username, String password) {
        User user = userMapper.findUserByNameAndPassword(username, password);
        return user;
    }

    public User findUserById(int userId) {
        User user = userMapper.findUserById(userId);
        return user;
    }

    public User findUserByBlogId(int blogId) {
        Blog blog = blogMapper.getBlogById(blogId);
        if (blog == null) {
            return null;
        }
        int userId = blog.getUserId();
        User user = userMapper.getOwnerBlogList(userId);
        List<Blog> blogList = user.getBlogList();
        for (Blog blog1: blogList) {
            String content = blog1.getContent();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < content.length(); i++) {
                if (content.charAt(i) != '*' && content.charAt(i) != '#' &&
                        content.charAt(i) != '~' && content.charAt(i) != '>') {
                    stringBuilder.append(content.charAt(i));
                }
            }
            blog1.setContent(stringBuilder.toString());
        }
        user.setBlogList(blogList);
        return user;

    }


}
