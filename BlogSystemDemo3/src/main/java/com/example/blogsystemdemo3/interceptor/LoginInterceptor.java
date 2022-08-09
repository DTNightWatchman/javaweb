package com.example.blogsystemdemo3.interceptor;

import com.example.blogsystemdemo3.model.User;
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
        // 对指定文件夹的拦截：通过reguest.get 获取请求路径，然后将请求路径的文件夹名和用户的文件夹名进行对比，如果相同就返回true，不同就返回false
        // 要从session中拿到用户的专属文件夹名
        return true;
    }
}
