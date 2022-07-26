package com.example.demo_java_oj.mapper;

import com.example.demo_java_oj.model.Problem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/25$ - 20:30
 */
@SpringBootTest
class ProblemMapperTest {
    @Autowired
    private ProblemMapper problemMapper;

    @Test
    void findProblemForTestCode() {
        Problem problem = problemMapper.findProblemForTestCode(1);
        System.out.println(problem);
    }

    @Test
    void findProblemForDetail() {
        Problem problem = problemMapper.findProblemForDetail(1);
        System.out.println(problem);
    }

    @Test
    void findAllProblems() {
        List<Problem> problems = problemMapper.findAllProblems();
        for (Problem problem: problems) {
            System.out.println(problem);
        }
    }
}