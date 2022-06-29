package com.example.blogsystemdemo2.mapper;

import com.example.blogsystemdemo2.model.Discuss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: YT
 * @Description: discussMapper
 * @DateTime: 2022/6/17 - 18:40
 */
@Mapper
public interface DiscussMapper {
    //增加评论
    public int insertDiscuss(Discuss discuss);

    //删除评论
    public int deleteDiscuss(int discussId);

    //查看某一篇博客的所有评论
    public List<Discuss> findAllDiscuss(int blogId);

    //查看discussId对应的discuss
    public Discuss findDiscussById(int discussId);
}
