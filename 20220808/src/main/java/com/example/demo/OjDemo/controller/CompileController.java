package com.example.demo.OjDemo.controller;

import com.example.demo.OjDemo.common.CodeUtil;
import com.example.demo.OjDemo.compile.Task;
import com.example.demo.OjDemo.model.Answer;
import com.example.demo.OjDemo.model.Question;
import com.example.demo.OjDemo.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-10
 * Time:16:30
 */
@RestController
public class CompileController {

    @Autowired
    private ProblemService problemService;

    static class CompileRequest {
        public int id;
        public String code;
    }

    static class CompileRespone {
        public int error;
        public String reason;
        public String stdout;
    }

    @PostMapping("/compile")
    public Answer compile(@RequestBody CompileRequest compileRequest) {
        // 判断代码是否正确
        boolean ans = CodeUtil.checkCodeSafe(compileRequest.code);
        if (ans == false) {
            Answer answer = new Answer();
            answer.setError(-1);
            answer.setReason("含恶意代码");
            return answer;
        }

        // 获取到通过id获取到测试代码
        String testCode = problemService.getTestCode(compileRequest.id);
        // 拼接代码（将测试代码和code拼接）
        String finalCode = CodeUtil.mergeCode(testCode,compileRequest.code);
        // 运行代码
        Task task = new Task();
        Question question = new Question();
        question.setCode(finalCode);
        Answer answer = task.compileAndRun(question);
        return answer;
    }
}
