package com.example.demo.OjDemo.compile;

import com.example.demo.OjDemo.model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-10
 * Time:16:22
 */
class TaskTest {

    @Test
    void compileAndRun() {
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "}");
        Task task = new Task();
        task.compileAndRun(question);
    }
}