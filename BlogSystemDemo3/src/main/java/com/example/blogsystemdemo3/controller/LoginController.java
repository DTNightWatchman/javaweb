package com.example.blogsystemdemo3.controller;

import com.example.blogsystemdemo3.model.User;
import com.example.blogsystemdemo3.model.UserSession;
import com.example.blogsystemdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: YT
 * @Description: 登录和注册
 * @DateTime: 2022/6/29$ - 18:19
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    static class LoginUser {
        public String username;
        public String password;
    }



    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody LoginUser loginUser, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        if (!StringUtils.hasLength(loginUser.username) || !StringUtils.hasLength(loginUser.password)) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","用户名或密码不能为空");
            return result;
        }
        UserSession userSession = userService.ifCouldLogin(loginUser.username, loginUser.username, loginUser.password);
        if (userSession != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userSession", userSession);
            result.put("success",200);
            result.put("data",1);
            result.put("message","登录成功！");
        } else {
            result.put("success",200);
            result.put("data",0);
            result.put("message","用户名或密码错误");
        }
        return result;
    }

    static class RegisterUser {
        public String username;
        public String github;
        public String email;
        public String password1;
        public String password2;
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Map<String,Object> register(@RequestBody RegisterUser registerUser) {
        Map<String,Object> result = new HashMap<>();
        if (!StringUtils.hasLength(registerUser.username) || !StringUtils.hasLength(registerUser.github)
        || !StringUtils.hasLength(registerUser.email) || !StringUtils.hasLength(registerUser.password1)
        || !StringUtils.hasLength(registerUser.password2)) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","信息不全");
            return result;
        }

        if (!registerUser.password1.equals(registerUser.password2)) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","两次密码不相同");
            return result;
        }

        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        boolean isMatch = Pattern.matches(emailMatcher,registerUser.email);
        if (!isMatch) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","email格式错误");
            return result;
        }
        System.out.println(registerUser.username);
        int userId = userService.ifExistUser(registerUser.username);
        if (userId != -1) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","用户名已经存在");
            return result;
        }

        User user = new User();
        user.setUsername(registerUser.username);
        user.setPassword(registerUser.password1);
        user.setGithub(registerUser.github);
        user.setEmail(registerUser.email);

        int ret = userService.insertUser(user);

        result.put("success",200);
        result.put("data",ret);
        result.put("message","注册成功");
        return result;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute("userSession");
        session.invalidate();
        response.sendRedirect("../index.html");
    }

}
