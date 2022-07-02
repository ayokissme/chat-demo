package com.demo.chat.service.impl;

import com.demo.chat.model.User;
import com.demo.chat.model.Views;
import com.demo.chat.repo.ConversationRepo;
import com.demo.chat.service.ConversationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ImageServiceImpl imageService;

    @Autowired
    public ConversationServiceImpl(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @Override
    public String getConversation(User user, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper
                .writerWithView(Views.NoPassword.class)
                .writeValueAsString(user);

        Map<Object, Object> frontendData = new HashMap<>();
        frontendData.put("user", result);
        model.addAttribute("frontendData", frontendData);

        return "/chat/chat";
    }

    @Override
    public ResponseEntity<?> getAllConversations(User user) {
        return ResponseEntity.ok().body(imageService.getAllConversationsData(user));
    }
}
