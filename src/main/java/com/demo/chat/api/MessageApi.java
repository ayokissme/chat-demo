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

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/message")
@Transactional
public class MessageApi {

    private final MessageServiceImpl messageService;

    @Autowired
    public MessageApi(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping("{id}")
    public ResponseEntity<HashMap<String, Object>> getAllChatMessages(
            @PathVariable("id") Long convId,
            @AuthenticationPrincipal User sender
    ) {
        return messageService.getMessages(convId, sender);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveMessage(
            @RequestBody MessageRequest messageRequest,
            @AuthenticationPrincipal User sender
    ) throws Exception {
        return messageService.saveMessage(messageRequest, sender);
    }
}
