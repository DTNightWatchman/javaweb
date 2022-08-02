package com.example.blogsystemdemo3.service;

import com.example.blogsystemdemo3.mapper.DiscussMapper;
import com.example.blogsystemdemo3.model.Discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/29$ - 14:18
 */
@Service
public class DiscussService {

    @Autowired
    private DiscussMapper discussMapper;

    public List<Discuss> getDiscusses(int blogId) {
        List<Discuss> discusses =  discussMapper.getDiscussesByblogId(blogId);
        return discusses;
    }

    public int addDiscuss(Discuss discuss) {
        discuss.setContent(discuss.getContent().replaceAll("<","&lt;"));
        discuss.setContent(discuss.getContent().replaceAll(">","&gt;"));
        return discussMapper.insertDiscuss(discuss);
    }

    public int deleteDiscuss(int discussId) {
        return discussMapper.deleteDiscuss(discussId);
    }

    public int getOwner(int discussId) {
        return discussMapper.getOwner(discussId);
    }
}
