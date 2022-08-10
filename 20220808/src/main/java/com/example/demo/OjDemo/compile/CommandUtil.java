package com.example.demo.OjDemo.compile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * created by YT
 * description: 用来创建一个新的进程执行代码
 * User:lenovo
 * Data:2022-08-10
 * Time:15:15
 */
public class CommandUtil {
    public static int run(String cmd, String stdoutFile, String stderrFile) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(cmd);
            if (stdoutFile != null) {
                InputStream stdoutFrom = process.getInputStream();
                OutputStream stdoutTo = new FileOutputStream(stdoutFile);
                while (true) {
                    int b = stdoutFrom.read();
                    if (b < 0) {
                        break;
                    }
                    stdoutTo.write(b);
                }
                stdoutFrom.close();
                stdoutTo.close();
            }
            if (stderrFile != null) {
                InputStream stderrFrom = process.getErrorStream();
                OutputStream stderrTo = new FileOutputStream(stderrFile);
                while (true) {
                    int b = stderrFrom.read();
                    if (b < 0) {
                        break;
                    }
                    stderrTo.write(b);
                }
                stderrFrom.close();
                stderrTo.close();
            }

            int exit = process.waitFor();
            return exit;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
