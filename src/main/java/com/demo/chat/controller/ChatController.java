package com.demo.chat.controller;

import com.demo.chat.model.User;
import com.demo.chat.model.Views;
import com.demo.chat.service.impl.ChatServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatServiceImpl chatService;

    @Autowired
    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    @JsonView(Views.Username.class)
    public String chatRoom(@AuthenticationPrincipal User user, Model model) throws JsonProcessingException {
        return chatService.getChatRoom(user, model);
    }
}
