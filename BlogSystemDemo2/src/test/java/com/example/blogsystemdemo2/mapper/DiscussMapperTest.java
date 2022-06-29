package com.example.blogsystemdemo2.mapper;

import com.example.blogsystemdemo2.component.BlackLogin;
import com.example.blogsystemdemo2.model.Discuss;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/17$ - 19:14
 */
@SpringBootTest
class DiscussMapperTest {

    @Autowired
    private DiscussMapper discussMapper;

    @Test
    void insertDiscuss() {
        //insert into discuss values (3,1,"YT","评论");
        Discuss discuss = new Discuss();
        discuss.setBlogId(3);
        discuss.setUserId(1);
        discuss.setUsername("YT");
        discuss.setPostTime(new Timestamp(System.currentTimeMillis()));
        discuss.setContent("discuss");
        int ret = discussMapper.insertDiscuss(discuss);
        System.out.println(ret);

    }

    @Test
    void deleteDiscuss() {
        int discussId = 2;
        int ret = discussMapper.deleteDiscuss(discussId);
        System.out.println(ret);
    }
    @Autowired
    private BlackLogin blackLogin;

    @Test
    void findAllDiscuss() {



//        List<Discuss> list = discussMapper.findAllDiscuss(3);
//        for (Discuss discuss: list) {
//            System.out.println(discuss);
//        }
    }

    @Test
    void findDiscussById() {
        Discuss discuss = discussMapper.findDiscussById(1);
        System.out.println(discuss);
    }
}