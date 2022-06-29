package com.example.blogsystemdemo2.controller;

import com.example.blogsystemdemo2.model.Blog;
import com.example.blogsystemdemo2.model.User;
import com.example.blogsystemdemo2.service.BlogService;
import com.example.blogsystemdemo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 用户管理
 * @DateTime: 2022/6/4$ - 11:53
 */

@ResponseBody
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/getuser")
    public Map<String,Object> getUser(int blogId, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User nowUser = (User) session.getAttribute("user");

        Blog blog = blogService.getBlogById(blogId);

        int userId = blog.getUserId();
        User user = userService.findUserById(userId);


        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());

        int isYourBlog = userId==nowUser.getUserId() ? 1:0;
        map.put("isYourBlog", isYourBlog);

        int number = user.getNumber();
        map.put("number", number);
        return map;
    }


    @RequestMapping("/usermessage")
    public Map<String,Object> userMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("../blog_login.html");
            return null;
        }
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("number", user.getNumber());
        return map;
    }

    @RequestMapping("/ownerbloglist")
    public Map<String,Object> ownerBlogList(int blogId) {
        User user = userService.findUserByBlogId(blogId);
        Map<String,Object> ret = new HashMap<>();
        ret.put("username",user.getUsername());
        ret.put("number",user.getNumber());
        ret.put("blogList",user.getBlogList());
        return ret;
    }
}
