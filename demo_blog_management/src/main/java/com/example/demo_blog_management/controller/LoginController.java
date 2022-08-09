package com.example.demo_blog_management.controller;

import com.example.demo_blog_management.model.Admin;
import com.example.demo_blog_management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @Description: 登录拦截
 * @DateTime: 2022/8/3$ - 9:19
 */
@Controller
@ResponseBody
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody Admin admin, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        result.put("success",200);
        if (!StringUtils.hasLength(admin.getUsername()) || !StringUtils.hasLength(admin.getPassword())) {
            result.put("data",0);
            result.put("message","账号或密码不能为空");
        }
        Admin ifLogin = adminService.ifLogin(admin.getUsername(), admin.getPassword());
        if (ifLogin != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("admin",admin);
            result.put("data",1);
            result.put("message","登录成功");
        } else {
            result.put("data",0);
            result.put("message","账号或密码错误");
        }
        return result;
    }
    // login/logout
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute("admin");
        session.invalidate();
        response.sendRedirect("../login.html");
        return "login.html";
    }

}
