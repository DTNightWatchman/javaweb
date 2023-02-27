package com.example.blogsystemdemo3.controller;

import com.example.blogsystemdemo3.model.Discuss;
import com.example.blogsystemdemo3.model.UserSession;
import com.example.blogsystemdemo3.service.DiscussService;
import com.example.blogsystemdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/29$ - 14:21
 */
@RestController
@RequestMapping("/discuss")
public class DiscussController {

    @Autowired
    private UserService userService;

    @Autowired
    private DiscussService discussService;

    static class ReqDiscuss {
        public int blogId;
        public String content;
    }

    @PostMapping("/addDiscuss")
    public Map<String,Object> addDiscuss(@RequestBody ReqDiscuss reqDiscuss, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();

        if (reqDiscuss.blogId == 0 || !StringUtils.hasLength(reqDiscuss.content)) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","评论不能为空");
            return result;
        }
        HttpSession session = request.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        int userId = userSession.getUserId();
        UserSession userMessage = userService.getUserMessage(userId);
        String username = userMessage.getUsername();
        int blogId = reqDiscuss.blogId;
        String content = reqDiscuss.content;
        Discuss discuss = new Discuss();
        discuss.setBlogId(blogId);
        discuss.setUserId(userId);
        discuss.setUsername(username);
        discuss.setContent(content);
        int ret = discussService.addDiscuss(discuss);
        result.put("success",200);
        result.put("data",ret);
        if (ret == 1) {
            result.put("message","评论成功");
        } else {
            result.put("message","评论失败");
        }
        return result;
    }


    @GetMapping("/deleteDiscuss")
    public Map<String,Object> deleteDiscuss(int discussId, HttpServletRequest request) {
        int ownerId = discussService.getOwner(discussId);
        HttpSession session = request.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        int userId = userSession.getUserId();
        Map<String,Object> result = new HashMap<>();
        if (ownerId != userId) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","不是你的评论，不可删除");
            return result;
        }
        int ret = discussService.deleteDiscuss(discussId);
        result.put("success",200);
        result.put("data",ret);
        if (ret == 1) {
            result.put("message","删除成功");
        } else {
            result.put("message","删除成功");
        }
        return result;
    }

    static class RespDiscess {
        public String username;
        public Timestamp postTime;
        public int discussId;
        public String content;
        public int isYourDiscuss;
    }

    @GetMapping("/getDiscuss")
    public Map<String,Object> getDiscuss(int blogId,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        List<Discuss> list = discussService.getDiscusses(blogId);
        List<RespDiscess> respDiscesses = new ArrayList<>();
        for (Discuss discuss:
             list) {
            RespDiscess respDiscess = new RespDiscess();
            respDiscess.username = discuss.getUsername();
            respDiscess.postTime = discuss.getPostTime();
            respDiscess.content = discuss.getContent();
            respDiscess.discussId = discuss.getDiscussId();
            respDiscess.isYourDiscuss = discuss.getUserId() == userSession.getUserId()? 1 : 0;
            respDiscesses.add(respDiscess);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("success",200);
        result.put("data",respDiscesses);
        return result;
    }
}
