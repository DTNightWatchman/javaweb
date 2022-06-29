package com.example.blogsystemdemo2.configration;

import com.example.blogsystemdemo2.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: YT
 * @Description: 拦截器配置
 * @DateTime: 2022/6/8$ - 9:21
 */

@Configuration
public class InterceptionConfigration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/blog_login.html","/css/common.css","/css/blog_login.css",
                "/image/**",
                "/login/login","/login/register","/register.html");
    }
}
