package com.example.demo_blog_management.mapper;

import com.example.demo_blog_management.model.Problem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/3$ - 16:34
 */
@SpringBootTest
class ProblemMapperTest {

    @Autowired
    private ProblemMapper problemMapper;

    @Test
    void getProblemDesc() {
        List<Problem> problems = problemMapper.getProblemDesc();
        for (Problem p :
                problems) {
            System.out.println(p);
        }
    }

    @Test
    void deleteProblem() {
        int ret = problemMapper.deleteProblem(28);
        System.out.println(ret);
    }

    @Test
    void updateProblem() {
        Problem problem = new Problem();
        problem.setId(33);
        problem.setTitle("2");
        problem.setLevel("3");
        problem.setDescription("4");
        problem.setTemplateCode("5");
        problem.setTestCode("6");
        int ret = problemMapper.updateProblem(problem);
        System.out.println(ret);
    }
}