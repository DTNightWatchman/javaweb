package com.yt.project.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 *
 * @author yt
 */
@Target(ElementType.METHOD)  // 用于描述方法
@Retention(RetentionPolicy.RUNTIME) // 确定注解保留范围，此处是.class文件依旧保留
public @interface AuthCheck {

    /**
     * 有任何一个角色
     *
     * @return
     */
    String[] anyRole() default "";

    /**
     * 必须有某个角色
     *
     * @return
     */
    String mustRole() default "";

}

