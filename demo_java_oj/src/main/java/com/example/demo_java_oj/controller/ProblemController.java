package com.example.demo_java_oj.controller;

import com.example.demo_java_oj.model.Problem;
import com.example.demo_java_oj.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 获取题目信息
 * @DateTime: 2022/7/25$ - 16:51
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {
    static class RetProblem {
        public int id;
        public String title;
        public String level;
    }

    static class ProblemDetail {
        public int id;
        public String title;
        public String level;
        public String description;
        public String templateCode;
    }

    @Autowired
    private ProblemService problemService;

    @GetMapping("/problem")
    public List<RetProblem> getProblems() {
        List<Problem> problems = problemService.getProblems();
        List<RetProblem> retProblems = new ArrayList<>();
        for (Problem problem: problems) {
            RetProblem retProblem = new RetProblem();
            retProblem.id = problem.getId();
            retProblem.title = problem.getTitle();
            retProblem.level = problem.getLevel();
            retProblems.add(retProblem);
        }
        return retProblems;
    }

    @GetMapping("/problemDetail")
    public ProblemDetail getProblems(int id) {
        Problem problem = problemService.getProblemDetail(id);
        ProblemDetail problemDetail = new ProblemDetail();
        problemDetail.id = problem.getId();
        problemDetail.title = problem.getTitle();
        problemDetail.level = problem.getLevel();
        problemDetail.description = problem.getDescription();
        problemDetail.templateCode = problem.getTemplateCode();
        return problemDetail;
    }


}
