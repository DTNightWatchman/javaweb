package com.example.demo.OjDemo.mapper;

import com.example.demo.OjDemo.model.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-09
 * Time:21:54
 */
@Mapper
public interface ProblemMapper {

    //增加题目
    public int addProblem(Problem problem);

    // 获取题目列表
    public List<Problem> getProblemList();

    // 获取题目详情
    public Problem getProblem(int id);

    // 获取测试代码
    public String getTestCode(int id);

}
