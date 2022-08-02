package com.example.blogsystemdemo3.configration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 异常捕获
 * @DateTime: 2022/8/1$ - 18:26
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    @ExceptionHandler(NullPointerException.class)
    public Map<String,Object> myException() {
        Map<String,Object> ret = new HashMap<>();
        ret.put("title","没有此篇博客");
        return ret;
    }

}
