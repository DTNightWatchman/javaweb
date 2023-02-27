package com.example.demo.OjDemo.compile;

import com.example.demo.OjDemo.common.FileUtil;
import com.example.demo.OjDemo.model.Answer;
import com.example.demo.OjDemo.model.Question;

import java.io.File;
import java.util.UUID;

/**
 * created by YT
 * description: 每发来一次代码，创建一个下的这个类，
 * User:lenovo
 * Data:2022-08-10
 * Time:14:31
 */
public class Task {
    // 约定工作目录
    private String WORK_DIR;
    // 约定class name
    private String CLASS;
    // 约定代码文件名
    private String CODE;
    // 约定存放编译错误的文件名
    private String COMPILE_ERROR;
    // 约定存放标准输出的文件名
    private String STDOUT;
    // 约定存放标准错误的文件名
    private String STDERR;
    public Task() {
        WORK_DIR = "./tmp/" + UUID.randomUUID().toString() + "/";
        CLASS = "Solution";
        CODE = WORK_DIR + "Solution.java";
        COMPILE_ERROR = WORK_DIR + "compileError.txt";
        STDOUT = WORK_DIR + "stdout.txt";
        STDERR = WORK_DIR + "stderr.txt";
    }

    public Answer compileAndRun(Question question) {
        Answer answer = new Answer();
        File file = new File(WORK_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        String content = question.getCode();
        FileUtil.writeFile(CODE,content);
        String cmd = String.format("javac -encoding utf8 %s -d %s",CODE,WORK_DIR);
        // 执行命令
        CommandUtil.run(cmd,null,COMPILE_ERROR);
        // 判断error中是否为空，如果为空，就说明编译时异常
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        if (!"".equals(compileError)) {
            // 说明编译异常，此时需要返回结果
            System.out.println("编译时异常");
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }

        // 编译成功，运行class文件
        String runCmd = String.format("java -classpath %s %s",WORK_DIR,CLASS);
        CommandUtil.run(runCmd,STDOUT,STDERR);
        // 看是否有运行异常
        String runError = FileUtil.readFile(STDERR);
        if (!"".equals(runError)) {
            // 运行时异常
            System.out.println("运行时异常");
            answer.setError(2);
            answer.setStderr(runError);
            return answer;
        }
        // 编译运行成功
        answer.setError(0);
        String stdout = FileUtil.readFile(STDOUT);
        answer.setStdout(stdout);
        return answer;
    }
}
