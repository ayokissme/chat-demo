package com.demo.chat.service.impl;

import com.demo.chat.model.User;
import com.demo.chat.model.Views;
import com.demo.chat.repo.ChatRepo;
import com.demo.chat.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String getChatRoom(User user, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper
                .writerWithView(Views.Username.class)
                .writeValueAsString(user);

        Map<Object, Object> frontendData = new HashMap<>();
        frontendData.put("user", result);
        model.addAttribute("frontendData", frontendData);
        return "/chat/chat-room";
    }
}
