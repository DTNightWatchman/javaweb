package com.example.demo.OjDemo.controller;

import com.example.demo.OjDemo.model.Problem;
import com.example.demo.OjDemo.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-10
 * Time:16:30
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;
    static class RespProblem {
        public int id;
        public String title;
        public String level;
    }
    @GetMapping("/getProblems")
    public List<RespProblem> getProblemList() {
        List<Problem> problems = problemService.getProblemList();
        List<RespProblem> respProblems = new ArrayList<>();
        for (Problem problem : problems) {
            RespProblem respProblem = new RespProblem();
            respProblem.id = problem.getId();
            respProblem.title = problem.getTitle();
            respProblem.level = problem.getLevel();
            respProblems.add(respProblem);
        }
        return respProblems;
    }

    static class RespProblemDesc {
        public int id;
        public String title;
        public String level;
        public String description;
        public String templateCode;
    }

    @GetMapping("/getProblem")
    public RespProblemDesc getProblemDesc(@RequestParam int id) {
        Problem problem = problemService.getProblem(id);
        RespProblemDesc respProblemDesc = new RespProblemDesc();
        respProblemDesc.id = problem.getId();
        respProblemDesc.title = problem.getTitle();
        respProblemDesc.level = problem.getLevel();
        respProblemDesc.description = problem.getDescription();
        respProblemDesc.templateCode = problem.getTemplateCode();
        return respProblemDesc;
    }
}
