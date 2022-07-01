package com.demo.chat.controller;

import com.demo.chat.model.request.MessageRequest;
import com.demo.chat.service.impl.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
public class WebSocketController {

    private final MessageServiceImpl messageService;

    @Autowired
    public WebSocketController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/queue/reply")
    public void sendMessage(
            @Payload MessageRequest messageRequest,
            Principal user
    ) throws Exception {
        messageService.sendMessageToUsers(messageRequest, user);
    }
}
