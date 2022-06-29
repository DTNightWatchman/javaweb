package com.example.blogsystemdemo2.component;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 黑名单
 * @DateTime: 2022/6/18$ - 23:42
 */
@Component
@Data
public class BlackLogin {


    private Map<String,Integer> blackMap;

    public BlackLogin() {
        blackMap = new HashMap<>();
    }

}
