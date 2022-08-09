package com.example.demo_blog_management.interceptor;

import com.example.demo_blog_management.model.Admin;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: YT
 * @Description: 登录拦截
 * @DateTime: 2022/8/5$ - 21:40
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        Admin admin = null;
        if (session != null) {
            admin = (Admin) session.getAttribute("admin");
        }
        if (session != null && admin != null) {
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
