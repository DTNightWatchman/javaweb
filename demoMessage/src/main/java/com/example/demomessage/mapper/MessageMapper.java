package com.example.demomessage.mapper;

import com.example.demomessage.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 无
 * @DateTime: 2022/6/30$ - 21:53
 */
@Mapper
public interface MessageMapper {
    //获取所有
    public List<Message> getAll();

    //插入操作
    public int insertMessage(Message message);
}
