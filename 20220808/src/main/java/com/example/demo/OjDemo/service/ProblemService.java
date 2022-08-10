package com.example.demo.OjDemo.service;

import com.example.demo.OjDemo.mapper.ProblemMapper;
import com.example.demo.OjDemo.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-10
 * Time:11:28
 */
@Service
public class ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    public int addProblem(Problem problem) {
        int ret = problemMapper.addProblem(problem);
        return ret;
    }

    public List<Problem> getProblemList() {
        List<Problem> problems = problemMapper.getProblemList();
        return problems;
    }

    public Problem getProblem(int id) {
        return problemMapper.getProblem(id);
    }

    public String getTestCode(int id) {
        String testCode = problemMapper.getTestCode(id);
        return testCode;
    }
}
