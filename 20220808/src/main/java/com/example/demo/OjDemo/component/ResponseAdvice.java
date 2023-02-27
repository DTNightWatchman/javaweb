package com.example.demo.OjDemo.component;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-10
 * Time:16:38
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        boolean flag = returnType.getGenericParameterType().equals(Map.class);
        return !false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Map<String,Object> ret = new HashMap<>();
        ret.put("success",200);
        ret.put("data",body);
        ret.put("message","成功");
        return ret;
    }
}
