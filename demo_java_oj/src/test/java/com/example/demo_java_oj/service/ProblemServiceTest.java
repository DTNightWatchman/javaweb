package com.example.demo_java_oj.service;

import com.example.demo_java_oj.model.Problem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/25$ - 20:56
 */
@SpringBootTest
class ProblemServiceTest {

    @Autowired
    private ProblemService problemService;

    @Test
    void getProblems() {
        List<Problem> list = problemService.getProblems();
        for (Problem problem: list) {
            System.out.println(problem);
        }
    }
}