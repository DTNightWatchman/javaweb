package com.example.demo20220526.configration;

import com.example.demo20220526.interceptor.LoginInterceptor;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: YT
 * @Description: 配置拦截器
 * @DateTime: 2022/5/26$ - 1:24
 */

@Configuration
public class InterceptorConfigration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/login.html","/login");
    }
}
