package com.example.demo_blog_management.service;

import com.example.demo_blog_management.mapper.ProblemMapper;
import com.example.demo_blog_management.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/3$ - 14:24
 */
@Service
public class ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    public int insertProblem(Problem problem) {
        int ret = problemMapper.insertProblem(problem);
        return ret;
    }

    public List<Problem> getProblemDesc() {
        List<Problem> problems = problemMapper.getProblemDesc();
        return problems;
    }

    public int deleteProblem(int id) {
        int ret = problemMapper.deleteProblem(id);
        return ret;
    }

    public int updateProblem(Problem problem) {
        int ret = problemMapper.updateProblem(problem);
        return ret;
    }

    public Problem getProblem(int id) {
        Problem problem = problemMapper.getProblem(id);
        return problem;
    }
}
