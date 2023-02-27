package com.example.blogsystemdemo3.component;

/**
 * @Author: YT
 * @Description: 构造运行代码
 * @DateTime: 2022/7/25$ - 21:21
 */
public class MergeCodeUtil {
    public static String mergeCode(String testCode, String requestCode) {
        int index = requestCode.lastIndexOf("}");
        if (index == -1) {
            // 非法代码
            return null;
        }
        String code1 = requestCode.substring(0,index);

        String finalCode = code1 + testCode + "\n}";

        return finalCode;
    }
}
