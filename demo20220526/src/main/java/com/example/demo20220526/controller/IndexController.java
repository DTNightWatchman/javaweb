package com.example.demo20220526.controller;

import com.example.demo20220526.model.User;
import org.apache.catalina.session.StandardSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 主页面
 * @DateTime: 2022/5/26$ - 1:27
 */

@Controller
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public String getIndex() {
        return "<h1>hello world</h1>";
    }

    //@ResponseBody
    @RequestMapping("/login")
    //@ResponseBody
    public void login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        String password = user.getPassword();

        if (StringUtils.hasLength(username) && StringUtils.hasLength((password))
        && username.equals("root") && password.equals("root")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user","root");
            response.setStatus(302);
            response.getWriter().write("");
        } else {
            response.sendRedirect("login.html");
        }
    }
}
