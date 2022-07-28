package com.example.blogsystemdemo3.service;

import com.example.blogsystemdemo3.mapper.ProblemMapper;
import com.example.blogsystemdemo3.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/25$ - 20:46
 */
@Service
public class ProblemService {


    @Resource
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
