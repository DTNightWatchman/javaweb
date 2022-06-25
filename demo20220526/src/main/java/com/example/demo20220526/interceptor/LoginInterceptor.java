package com.example.demo20220526.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: YT
 * @Description: 登录拦截
 * @DateTime: 2022/5/26$ - 1:19
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html;charset=utf8");
        HttpSession session = request.getSession(false);
        System.out.println(session);
        if (session == null) {
            response.sendRedirect("/login.html");
            response.getWriter().write("<script>alert(\"账号或密码错误\")</script>");
            return false;
        }
        return true;
    }
}
