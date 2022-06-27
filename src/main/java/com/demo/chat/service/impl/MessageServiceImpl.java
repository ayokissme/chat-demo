package com.demo.chat.service.impl;

import com.demo.chat.model.Message;
import com.demo.chat.model.User;
import com.demo.chat.model.enums.MessageStatus;
import com.demo.chat.model.request.MessageRequest;
import com.demo.chat.repo.MessageRepo;
import com.demo.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.demo.chat.model.enums.MessageStatus.DELIVERED;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    @Override
    public ResponseEntity<?> saveMessage(MessageRequest messageRequest, User sender) throws Exception {
        Message message = new Message();
        message.setSender(sender);
        message.setTimestamp(LocalDateTime.now());
        message.setStatus(DELIVERED);
        messageRepo.save(message);
        return ResponseEntity.ok().build();
    }
}
