package com.example.demomessage;

import com.example.demomessage.model.Message;
import com.example.demomessage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/30$ - 22:22
 */

@Controller
@ResponseBody
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/getall")
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @RequestMapping("/message")
    public int insertMessage(@RequestBody Message message) {
        int ret = messageService.insertMessage(message);
        return ret;
    }


}
