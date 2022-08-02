package com.example.blogsystemdemo3.configration;

import com.example.blogsystemdemo3.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: YT
 * @Description: 登录拦截
 * @DateTime: 2022/8/1$ - 18:40
 */
@Configuration
public class InterceptionConfigration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/index.html","/css/**","/fonts/**","/images/**",
                "/login/login","/login/register","/js/**");
    }
}
