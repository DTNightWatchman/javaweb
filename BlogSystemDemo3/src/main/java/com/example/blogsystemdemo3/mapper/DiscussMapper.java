package com.example.blogsystemdemo3.mapper;

import com.example.blogsystemdemo3.model.Discuss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: YT
 * @Description: discuss
 * @DateTime: 2022/7/29$ - 14:07
 */
@Mapper
public interface DiscussMapper {
    public int insertDiscuss(Discuss discuss);

    public int deleteDiscuss(int discussId);

    public List<Discuss> getDiscussesByblogId(int blogId);

    public int getOwner(int discussId);

}
