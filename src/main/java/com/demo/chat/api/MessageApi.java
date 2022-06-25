package com.demo.chat.api;

import com.demo.chat.model.Message;
import com.demo.chat.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageApi {

    private final MessageServiceImpl messageService;

    @Autowired
    public MessageApi(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/all")
    public List<Message> getAllChatMessages() {
        return messageService.getAllMessages();
    }
}
