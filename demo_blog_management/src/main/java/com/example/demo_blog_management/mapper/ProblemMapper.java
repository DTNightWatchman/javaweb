package com.example.demo_blog_management.mapper;

import com.example.demo_blog_management.model.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/3$ - 14:18
 */
@Mapper
public interface ProblemMapper {

    // 添加题目
    public int insertProblem(Problem problem);

    // 获取题目列表
    public List<Problem> getProblemDesc();

    // 删除某个题目
    public int deleteProblem(int id);

    // 修改某个题目
    public int updateProblem(Problem problem);

    public Problem getProblem(int id);

}
