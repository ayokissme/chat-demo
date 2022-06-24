package com.demo.chat.controller;

import com.demo.chat.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message getMessage(final Message message) {
        return message;
    }

}
