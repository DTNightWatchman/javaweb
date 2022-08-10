package com.example.demo.OjDemo.common;

import java.io.*;

/**
 * created by YT
 * description: 文件操作
 * User:lenovo
 * Data:2022-08-10
 * Time:15:08
 */
public class FileUtil {

    public static String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try(Reader reader = new FileReader(filePath)) {
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

    public static void writeFile(String filePath, String content) {
        try(Writer writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
