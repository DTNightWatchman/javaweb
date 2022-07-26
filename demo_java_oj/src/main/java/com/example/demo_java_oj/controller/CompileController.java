package com.example.demo_java_oj.controller;

import com.example.demo_java_oj.compile.Task;
import com.example.demo_java_oj.component.CheckCodeUtil;
import com.example.demo_java_oj.component.MergeCodeUtil;
import com.example.demo_java_oj.exception.CodeInValidException;
import com.example.demo_java_oj.exception.ProblemNotFoundException;
import com.example.demo_java_oj.model.Answer;
import com.example.demo_java_oj.model.Problem;
import com.example.demo_java_oj.model.Question;
import com.example.demo_java_oj.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: YT
 * @Description: 编译controller
 * @DateTime: 2022/7/25$ - 21:08
 */
@RestController
public class CompileController {

    @Resource
    private ProblemService problemService;

    static class CompileRequest {
        public int id;
        public String code;
    }

    static class CompileResponse {
        public int error;
        public String reason;
        public String stdout;
    }

    @Resource
    private CheckCodeUtil checkCodeUtil;

    @PostMapping("/compile")
    public CompileResponse compileAndRun(@RequestBody CompileRequest compileRequest) {
        // 编译和运行
        CompileResponse compileResponse = new CompileResponse();

        try {
            // 检验代码安全性
            boolean flag = checkCodeUtil.checkCode(compileRequest.code);
            if (!flag) {
                throw new CodeInValidException();
            }
            Problem problem = problemService.getProblemTestCode(compileRequest.id);
            if (problem == null) {
                throw new ProblemNotFoundException();
            }
            String finalCode = MergeCodeUtil.mergeCode(problem.getTestCode(),compileRequest.code);
            if (finalCode == null) {
                throw new CodeInValidException();
            }
            Task task = new Task();
            Question question = new Question();
            question.setCode(finalCode);
            Answer answer = task.compileAndRun(question);

            compileResponse.error = answer.getError();
            compileResponse.reason = answer.getReason();
            compileResponse.stdout = answer.getStdout();

        } catch (ProblemNotFoundException e) {
            compileResponse.error = -1;
            compileResponse.reason = "没找到指定题目";
        } catch (CodeInValidException e) {
            compileResponse.error = -1;
            compileResponse.reason = "代码不合法";
        }
        return compileResponse;

    }

}
