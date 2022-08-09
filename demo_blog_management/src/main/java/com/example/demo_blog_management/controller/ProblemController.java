package com.example.demo_blog_management.controller;

import com.example.demo_blog_management.model.Problem;
import com.example.demo_blog_management.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 题目控制模块
 * @DateTime: 2022/8/3$ - 14:18
 */

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping("/insertProblem")
    public Map<String,Object> insertProblem(@RequestBody Problem problem) {
        Map<String,Object> result = new HashMap<>();
        if (!StringUtils.hasLength(problem.getTitle()) || !StringUtils.hasLength(problem.getDescription())
        || !StringUtils.hasLength(problem.getTemplateCode()) || !StringUtils.hasLength(problem.getTestCode())) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","题目信息不全");
            return result;
        }
        if (!(problem.getLevel().equals("简单") || problem.getLevel().equals("中等") || problem.getLevel().equals("困难"))) {
            problem.setLevel("未知");
        }
        int ret = problemService.insertProblem(problem);
        result.put("success",200);
        result.put("data",ret);
        if (ret == 1) {
            result.put("message","添加题目成功");
        } else {
            result.put("message","添加题目失败");
        }
        return result;
    }

    static class ProblemDesc {
        public int id;
        public String title;
        public String level;
    }


    // http://localhost:9091/problem/getProblemDesc
    @RequestMapping("/getProblemDesc")
    public List<ProblemDesc> getProblemDesc() {
        List<Problem> problems = problemService.getProblemDesc();
        List<ProblemDesc> problemDescs = new ArrayList<>();
        for (Problem problem : problems) {
            ProblemDesc problemDesc = new ProblemDesc();
            problemDesc.id = problem.getId();
            problemDesc.title = problem.getTitle();
            problemDesc.level = problem.getLevel();
            problemDescs.add(problemDesc);
        }
        return problemDescs;
    }


    // http://localhost:9091/problem/deleteProblem?id=30
    @GetMapping("/deleteProblem")
    public Map<String,Object> deleteProblem(int id) {
        int ret = problemService.deleteProblem(id);
        Map<String,Object> result = new HashMap<>();
        result.put("success",200);
        result.put("data",ret);
        if (ret == 1) {
            result.put("message","删除成功");
        } else {
            result.put("message","删除失败");
        }
        return result;
    }

    @PostMapping("/updateProblem")
    public Map<String,Object> updateProblem(@RequestBody Problem problem) {
        int ret = problemService.updateProblem(problem);
        Map<String,Object> result = new HashMap<>();
        result.put("success",200);
        result.put("data",ret);
        if (ret == 1) {
            result.put("message","修改成功");
        } else {
            result.put("message","修改失败");
        }
        return result;
    }

    @GetMapping("/getProblem")
    public Problem getProblem(Integer id) {
        if (id == null) {
            return new Problem();
        }
        Problem problem = problemService.getProblem(id);
        return problem;
    }
}
