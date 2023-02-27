package com.example.demo_java_oj.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/25$ - 17:46
 */
@SpringBootTest
class FileUtilTest {
    @Autowired
    private FileUtil fileUtil;

    @Test
    void writeFile() {

        fileUtil.writeFile("./stdout.txt","1111");
    }

    @Test
    void readFile() {
        System.out.println(System.getProperty("user.dir"));
        String str = fileUtil.readFile("./stderr.txt");
        System.out.println(str);
    }
}