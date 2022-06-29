package com.example.blogsystemdemo2.service;

import com.example.blogsystemdemo2.mapper.DiscussMapper;
import com.example.blogsystemdemo2.model.Discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YT
 * @Description: 讨论服务
 * @DateTime: 2022/6/17 - 20:51
 */
@Service
public class DiscussService {
    @Autowired
    DiscussMapper discussMapper;

    public int insertDiscuss(Discuss discuss) {
        int ret = discussMapper.insertDiscuss(discuss);
        return ret;
    }

    public int deleteDiscuss(int discussId) {
        int ret = discussMapper.deleteDiscuss(discussId);
        return ret;
    }

    public List<Discuss> findAllDiscuss(int blogId, int nowUserId) {
        List<Discuss> discussList = discussMapper.findAllDiscuss(blogId);
        for (Discuss discuss: discussList) {
            if (discuss.getUserId() == nowUserId) {
                discuss.setIsYourDiscuss(1);
            } else {
                discuss.setIsYourDiscuss(0);
            }
        }
        return discussList;
    }

    public int deleteDiscussById(int discussId) {
        int ret = discussMapper.deleteDiscuss(discussId);
        return ret;
    }

    public Discuss findDiscussById(int discussId) {
        Discuss discuss = discussMapper.findDiscussById(discussId);
        return discuss;
    }


}
