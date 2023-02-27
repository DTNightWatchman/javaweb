package com.example.demo.OjDemo.common;

import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.ArrayList;
import java.util.List;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-10
 * Time:17:24
 */
public class CodeUtil {
    public static String mergeCode(String testCode, String code) {
        int index = code.lastIndexOf("}");
        if (index == -1) {
            return null;
        }
        String code1 = code.substring(0,index);
        String finalCode = code1 + testCode + "\n}";
        return finalCode;
    }

    private static List<String> blackList = new ArrayList<>();
    public CodeUtil() {
        blackList.add("Runtime");
        blackList.add("exec");
        blackList.add("java.io");
        blackList.add("java.net");
    }

    public static boolean checkCodeSafe(String code) {
        for (String blackStr : blackList) {
            if (code.indexOf(blackStr) > 0) {
                return false;
            }
        }
        return true;
    }
}
