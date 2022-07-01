package com.example.demomessage.model;

import lombok.Data;

/**
 * @Author: YT
 * @Description: 单元
 * @DateTime: 2022/6/30$ - 21:55
 */

@Data
public class Message {
    private String from;
    private String to;
    private String message;
}
