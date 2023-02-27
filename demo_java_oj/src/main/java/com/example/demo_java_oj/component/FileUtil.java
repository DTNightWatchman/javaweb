package com.example.demo_java_oj.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @Author: YT
 * @Description: 文件操作
 * @DateTime: 2022/7/25$ - 17:35
 */
@Component
public class FileUtil {
    public void writeFile(String filePath, String content) {
        try(Writer writer = new FileWriter(filePath)) {
            writer.write(content);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Reader reader = new FileReader(filePath)) {
            while (true) {
                int ch = reader.read();
                if (ch < 0) {
                    break;
                }
                stringBuilder.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }



}
