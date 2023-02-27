package com.example.demomessage.mapper;

import com.example.demomessage.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/30$ - 22:13
 */
@SpringBootTest
class messageMapperTest {

    @Autowired
    private MessageMapper messageMapper;

    @Test
    void getAll() {
        List<Message> messageList = messageMapper.getAll();

        for (Message message: messageList) {
            System.out.println(message);
        }
    }
}