package com.example.blogsystemdemo2.controller;

import com.example.blogsystemdemo2.model.Blog;
import com.example.blogsystemdemo2.model.User;
import com.example.blogsystemdemo2.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 博客管理
 * @DateTime: 2022/6/3$ - 11:55
 */

@ResponseBody
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/addblog")
    public void addBlog(String title, String content, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Blog blog = new Blog();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        int num = user.getNumber() + 1;
        user.setNumber(num);
        session.setAttribute("user",user);
        blog.setUserId(user.getUserId());
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        //System.out.println(blog);
        int ret1 = blogService.insertBlog(blog);
        if (ret1 == 1) {
            System.out.println("插入文章成功");
        }
        response.sendRedirect("../blog_list.html");
    }

    @RequestMapping("/bloglist")
    public List<Blog> blogList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        if (request.getSession(false) == null) {
            response.sendRedirect("../blog_login.html");
            return null;
        }
        List<Blog> list = blogService.getAllBlogs();

        return list;
    }


    @RequestMapping("/getblog")
    public Blog getBlogById(int blogId) {
        Blog blog = blogService.getBlogById(blogId);
        return blog;
    }

    @RequestMapping("/blogDelete")
    public Map<String, Object> deleteBlog(int blogId, HttpServletRequest request,HttpServletResponse response) throws IOException {
        Blog blog = blogService.getBlogById(blogId);
        int userId = blog.getUserId();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Map<String,Object> map = new HashMap<>();
        //判断要删除的博客是不是属于发送请求的用户，如果是才允许删除，如果不是就直接回到list页面，不允许删除。
        if (userId != user.getUserId()) {
            map.put("success",0);
            map.put("message","删除失败");
            response.sendRedirect("../blog_list.html");
            return map;
        }
        int t = blogService.deleteBlogById(blogId);

        map.put("success", t);
        String message = "删除成功";
        map.put("message", message);

        int number = user.getNumber() - 1;
        user.setNumber(number);
        session.setAttribute("user", user);
        return map;
    }
}
