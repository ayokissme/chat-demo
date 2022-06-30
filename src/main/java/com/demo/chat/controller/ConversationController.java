package com.demo.chat.controller;

import com.demo.chat.model.User;
import com.demo.chat.service.impl.ConversationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ConversationController {

    private final ConversationServiceImpl conversationService;

    @Autowired
    public ConversationController(ConversationServiceImpl conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping
    public String conversation(@AuthenticationPrincipal User user, Model model)
            throws JsonProcessingException {
        return conversationService.getConversation(user, model);
    }
}
