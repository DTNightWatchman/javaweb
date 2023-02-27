package com.example.blogsystemdemo3.mapper;

import com.example.blogsystemdemo3.model.Problem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/27$ - 20:22
 */
@SpringBootTest
class ProblemMapperTest {
    @Autowired
    ProblemMapper problemMapper;

    @Test
    void findAllProblems() {
        List<Problem> problems = problemMapper.findAllProblems();
        for (Problem problem:
             problems) {
            System.out.println(problem);
        }
    }
}