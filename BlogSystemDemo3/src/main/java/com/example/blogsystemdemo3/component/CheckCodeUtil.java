package com.example.blogsystemdemo3.component;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YT
 * @Description: 检查代码安全性
 * @DateTime: 2022/7/26$ - 10:42
 */
@Component
public class CheckCodeUtil {
    private List<String> blackList = new ArrayList<>();

    public CheckCodeUtil() {
        this.blackList.add("Runtime");
        this.blackList.add("exec");
        this.blackList.add("java.io");
        this.blackList.add("java.net");
    }

    public boolean checkCode(String code) {
        for (String blackStr : blackList) {
            if (code.indexOf(blackStr) > 0) {
                return false;
            }
        }
        return true;
    }
}
