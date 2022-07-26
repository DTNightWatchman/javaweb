package com.example.demo_java_oj.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo_java_oj.model.Problem;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/25$ - 20:11
 */
@Mapper
public interface ProblemMapper {
    // 插入题目
    public int insertProblem(Problem problem);

    // 删除题目
    public int deleteProblem(int id);

    // 根据id查找题目
    public Problem findProblemForTestCode(int id);

    public Problem findProblemForDetail(int id);

    // 查找所有题目
    public List<Problem> findAllProblems();
}
