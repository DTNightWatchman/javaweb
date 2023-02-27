package com.example.demomessage.service;

import com.example.demomessage.mapper.MessageMapper;
import com.example.demomessage.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YT
 * @Description: service
 * @DateTime: 2022/6/30$ - 22:19
 */
@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public List<Message> getAllMessages() {
        List<Message> messageList = messageMapper.getAll();
        return messageList;

    }

    public int insertMessage(Message message) {
        int ret = messageMapper.insertMessage(message);
        return ret;
    }




}
