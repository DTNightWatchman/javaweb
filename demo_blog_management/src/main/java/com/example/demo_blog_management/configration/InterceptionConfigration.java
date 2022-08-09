package com.example.demo_blog_management.configration;

import com.example.demo_blog_management.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/5$ - 21:44
 */
@Configuration
public class InterceptionConfigration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/js/**","/images/**","/login.html","/css/**","/fonts/**","/login/login");
    }
}
