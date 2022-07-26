package com.example.demo_java_oj.component;

import jdk.internal.util.xml.impl.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @Author: YT
 * @Description: 编译组件
 * @DateTime: 2022/7/25$ - 16:54
 */
@Component
public class CompileComponent {
    public int run(String cmd, String stdoutFile, String stderrFile) {
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
            int ret = process.waitFor();
            return ret;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
