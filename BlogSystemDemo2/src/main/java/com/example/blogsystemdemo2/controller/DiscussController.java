package com.example.blogsystemdemo2.controller;

import com.example.blogsystemdemo2.model.Discuss;
import com.example.blogsystemdemo2.model.User;
import com.example.blogsystemdemo2.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

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
 * @Description: 评论管理
 * @DateTime: 2022/6/17$ - 20:29
 */
@ResponseBody
@RequestMapping("/discuss")
@Controller
public class DiscussController {
    @Autowired
    DiscussService discussService;

    //增加评论控制模块
    @RequestMapping("/adddiscuss")
    public Map<String,Object> addDiscuss(@RequestBody Discuss discuss, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<>();
        if (discuss.getContent().equals("")) {
            map.put("success",0);
            map.put("message","评论内容不能为空");
            return map;
        }
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        //获取当前用户的信息
        int userId = user.getUserId();
        String username = user.getUsername();
        discuss.setUserId(userId);
        discuss.setUsername(username);
        discuss.setPostTime(new Timestamp(System.currentTimeMillis()));
        int ret = discussService.insertDiscuss(discuss);
        map.put("success",ret);
        if (ret == 1) {
            map.put("message", "评论成功");
        } else {
            map.put("message", "评论失败");
        }
        return map;
    }

    //展示所有评论
    @RequestMapping("/discusslist")
    public List<Discuss> ShowDiscusses(int blogId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User nowUser = (User) session.getAttribute("user");
        List<Discuss> discussList = discussService.findAllDiscuss(blogId,nowUser.getUserId());
        return discussList;
    }

    //删除对应的评论
    @RequestMapping("/deletediscuss")
    public Map<String, Object> deleteDiscuss(int discussId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        int nowUserId = user.getUserId();
        Discuss discuss = discussService.findDiscussById(discussId);
        int blogId = discuss.getBlogId();

        if (discuss == null) {
            map.put("success",0);
        }
        if (discuss.getUserId() != nowUserId) {
            map.put("success",0);
        }
        // 是评论的作者就删除评论
        int ret = discussService.deleteDiscuss(discussId);
        map.put("success", ret);
        map.put("blogId", blogId);
        return map;
    }
}
