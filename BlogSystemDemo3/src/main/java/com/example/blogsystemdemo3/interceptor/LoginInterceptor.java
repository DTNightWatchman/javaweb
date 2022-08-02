package com.example.blogsystemdemo3.interceptor;

import com.example.blogsystemdemo3.model.UserSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: YT
 * @Description: 登录拦截
 * @DateTime: 2022/8/1$ - 18:37
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html;charset=utf8");
        HttpSession session = request.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        if (session == null || userSession == null) {
            response.sendRedirect("index.html");
            return false;
        }
        return true;
    }
}
