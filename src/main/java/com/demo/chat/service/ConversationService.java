package com.demo.chat.service;

import com.demo.chat.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface ConversationService {
    String getConversation(User user, Model model) throws JsonProcessingException;
    ResponseEntity<?> getAllConversations(User user);
    ResponseEntity<?> getConversationAddress(User me, User otherUser);
}
