package com.example.blogsystemdemo3.controller;

import com.example.blogsystemdemo3.model.UserSession;
import com.example.blogsystemdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YT
 * @Description: user
 * @DateTime: 2022/7/28$ - 12:41
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userMessage")
    public UserSession getUserMessage(int userId) {
        UserSession userSession = userService.getUserMessage(userId);
        return userSession;
    }
}
