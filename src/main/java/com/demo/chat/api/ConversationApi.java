package com.demo.chat.api;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.User;
import com.demo.chat.service.impl.ConversationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conversation")
public class ConversationApi {

    private final ConversationServiceImpl conversationService;

    @Autowired
    public ConversationApi(ConversationServiceImpl conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Conversation>> allConversation(@AuthenticationPrincipal User user) {
        return conversationService.getAllConversations(user);
    }
}
