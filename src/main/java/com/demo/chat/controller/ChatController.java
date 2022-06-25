package com.demo.chat.controller;

import com.demo.chat.model.User;
import com.demo.chat.service.impl.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatServiceImpl chatService;

    @Autowired
    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String chatRoom(@AuthenticationPrincipal User user, Model model) {
        Map<Object, Object> frontendData = new HashMap<>();
        frontendData.put("user", user.getId());
        model.addAttribute("frontendData", frontendData);
        return "/chat/chat-room";
    }
}
