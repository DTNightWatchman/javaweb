package com.example.demo_java_oj.service;

import com.example.demo_java_oj.mapper.ProblemMapper;
import com.example.demo_java_oj.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/25$ - 20:46
 */
@Service
public class ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    public List<Problem> getProblems() {
        List<Problem> problems = problemMapper.findAllProblems();
        return problems;
    }

    public Problem getProblemDetail(int id) {
        Problem problem = problemMapper.findProblemForDetail(id);
        return problem;
    }

    public Problem getProblemTestCode(int id) {
        Problem problem = problemMapper.findProblemForTestCode(id);
        return problem;
    }
}
