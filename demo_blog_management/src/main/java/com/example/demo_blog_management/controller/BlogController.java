package com.example.demo_blog_management.controller;

import com.example.demo_blog_management.model.Blog;
import com.example.demo_blog_management.model.BlogDesc;
import com.example.demo_blog_management.service.BlogService;
import com.example.demo_blog_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.applet.resources.MsgAppletViewer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YT
 * @Description: blog控制
 * @DateTime: 2022/8/4$ - 9:59
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    static class RespBlogDesc {
        public int blogId;
        public String title;
        public String username;
        public Timestamp postTime;
        public String status;
    }

    @RequestMapping("/getList")
    public List<RespBlogDesc> getBlogDesc() {
        List<BlogDesc> blogDescs = blogService.getBlogDescs();
        List<RespBlogDesc> result = new ArrayList<>();
        for (BlogDesc blogDesc : blogDescs) {
            RespBlogDesc respBlogDesc = new RespBlogDesc();
            respBlogDesc.blogId = blogDesc.getBlogId();
            respBlogDesc.title = blogDesc.getTitle();
            respBlogDesc.username = userService.getUsername(blogDesc.getUserId());
            respBlogDesc.postTime = blogDesc.getPostTime();
            respBlogDesc.status = blogDesc.getStatus() == 1? "否" : "是";
            result.add(respBlogDesc);
        }
        return result;
    }

    @RequestMapping("/deleteBlogDesc")
    public Map<String,Object> deleteBlogDesc(int blogId) {
        int ret = blogService.deleteBlogDesc(blogId);
        Map<String,Object> result = new HashMap<>();
        result.put("success",200);
        if (ret == 1) {
            result.put("data",1);
            result.put("message","删除成功");
        } else {
            result.put("data",0);
            result.put("message","删除失败");
        }
        return result;
    }

    @RequestMapping("/recoverBlog")
    public Map<String,Object> recoverBlog(int blogId) {
        int ret = blogService.recoverBlog(blogId);
        Map<String,Object> result = new HashMap<>();
        result.put("success",200);
        if (ret == 1) {
            result.put("data",1);
            result.put("message","恢复成功");
        } else {
            result.put("data",0);
            result.put("message","恢复失败");
        }
        return result;
    }

    @RequestMapping("/deleteBlog")
    public Map<String,Object> deleteBlog(int blogId) {
        Map<String,Object> result = new HashMap<>();
        result.put("success",200);
        int ret = blogService.deleteBlog(blogId);
        if (ret == 1) {
            result.put("data",1);
            result.put("message","彻底删除成功");
        } else {
            result.put("data",0);
            result.put("message","彻底删除失败");
        }
        return result;
    }

    @RequestMapping("/getBlog")
    public Blog getBlog(int blogId) {
        Blog blog = blogService.getBlogById(blogId);
        return blog;
    }

    static class ReqBlog {
        public String title;
        public String content;
    }

    @PostMapping("/updateBlog")
    public Map<String,Object> updateBlog(int blogId, @RequestBody ReqBlog reqBlog) {
        int ret = blogService.updateBlog(blogId,reqBlog.title,reqBlog.content);
        Map<String,Object> result = new HashMap<>();
        result.put("success",200);
        result.put("data",ret);
        if (ret == 1) {
            result.put("message","修改成功");
        } else {
            result.put("message","修改失败");
        }
        return result;
    }

}
