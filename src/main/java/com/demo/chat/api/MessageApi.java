package com.demo.chat.api;

import com.demo.chat.model.Message;
import com.demo.chat.model.User;
import com.demo.chat.service.impl.MessageServiceImpl;
import com.demo.chat.model.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@Transactional
public class MessageApi {

    private final MessageServiceImpl messageService;

    @Autowired
    public MessageApi(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/all")
    public List<Message> getAllChatMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveMessage(
            @RequestBody MessageRequest messageRequest,
            @AuthenticationPrincipal User sender
    ) throws Exception {
        return messageService.saveMessage(messageRequest, sender);
    }
}
