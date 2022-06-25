package com.demo.chat.service.impl;

import com.demo.chat.model.User;
import com.demo.chat.repo.ChatRepo;
import com.demo.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepo chatRepo;

    @Autowired
    public ChatServiceImpl(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }

    @Override
    public String listChats(User user) {
        chatRepo.findAllChats(user.getId());
        return null;
    }
}
