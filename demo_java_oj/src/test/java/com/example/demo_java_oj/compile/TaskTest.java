package com.example.demo_java_oj.compile;

import com.example.demo_java_oj.component.CompileComponent;
import com.example.demo_java_oj.model.Answer;
import com.example.demo_java_oj.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/25$ - 19:21
 */
//@SpringBootTest
class TaskTest {
    @Autowired
    private CompileComponent compileComponent;

    @Test
    void compileAndRun() {
        Task task = new Task();
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "}");
        Answer answer = task.compileAndRun(question);
        System.out.println(answer.getError());
        System.out.println(answer.getReason());
    }
}