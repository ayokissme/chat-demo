package com.demo.chat.service.impl;

import com.demo.chat.model.Message;
import com.demo.chat.repo.MessageRepo;
import com.demo.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
