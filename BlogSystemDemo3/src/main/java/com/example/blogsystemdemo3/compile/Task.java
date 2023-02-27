package com.example.blogsystemdemo3.compile;

import com.example.blogsystemdemo3.component.CompileComponent;
import com.example.blogsystemdemo3.component.FileUtil;
import com.example.blogsystemdemo3.model.Answer;
import com.example.blogsystemdemo3.model.Question;
import com.example.blogsystemdemo3.component.CompileComponent;
import com.example.blogsystemdemo3.component.FileUtil;
import com.example.blogsystemdemo3.model.Answer;
import com.example.blogsystemdemo3.model.Question;

import java.io.File;
import java.util.UUID;

/**
 * @Author: YT
 * @Description: 任务
 * @DateTime: 2022/7/25$ - 17:03
 */
public class Task {
    // 运行时的临时文件目录
    private String WORK_DIR;

    // 约定代码的类名
    private String CLASS;

    // 约定文件名/类名
    private String CODE;

    // 存放编译时异常
    private String COMPILE_ERROR;

    // 标准输出
    private String STDOUT;

    // 标准异常
    private String STDERR;

    public Task() {
        this.WORK_DIR = "./tmp/" + UUID.randomUUID().toString() + "/";
        this.CLASS = "Solution";
        this.CODE =  WORK_DIR + "Solution.java";
        this.COMPILE_ERROR = WORK_DIR + "compileError.txt";
        this.STDOUT = WORK_DIR + "stdout.txt";
        this.STDERR = WORK_DIR + "stderr.txt";
    }

    private FileUtil fileUtil = new FileUtil();

    private CompileComponent compileComponent = new CompileComponent();

    public Answer compileAndRun(Question question) {
        // 用例存放结果
        Answer answer = new Answer();

        // 创建临时目录
        File file = new File(WORK_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        String content = question.getCode();
        fileUtil.writeFile(CODE,content);
        String compileCmd = String.format("javac -encoding utf8 %s -d %s", CODE, WORK_DIR);
        // 如果编译出错，compileError.txt 中有内容
        compileComponent.run(compileCmd,null,COMPILE_ERROR);
        String compileError = fileUtil.readFile(COMPILE_ERROR);
        if (!"".equals(compileError)) {
            // 编译出错
            System.out.println(compileError);
            System.out.println("编译错误");
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }

        String runCmd = String.format("java -classpath %s %s", WORK_DIR, CLASS);
        compileComponent.run(runCmd, STDOUT, STDERR);
        String stdError = fileUtil.readFile(STDERR);
        if (!"".equals(stdError)) {
            // 运行出错
            System.out.println("运行出错");
            answer.setError(2);
            answer.setReason(stdError);
            return answer;
        }

        // 编译运行成功
        System.out.println("编译运行成功");
        answer.setError(0);
        answer.setStdout(fileUtil.readFile(STDOUT));
        return answer;
    }

    public static void main(String[] args) {
        Task task = new Task();
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "}");
        Answer answer = task.compileAndRun(question);
        System.out.println(answer.getStdout());
    }
}
