package com.example.blogsystemdemo2.interceptor;

import com.example.blogsystemdemo2.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: YT
 * @Description: 登录拦截
 * @DateTime: 2022/6/8$ - 9:15
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html;charset=utf8");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/blog_login.html?v=1");
            response.getWriter().write("<script>alert(\"账号或密码错误\")</script>");
            return false;
        }
        return true;
    }
}
