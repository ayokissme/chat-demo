package com.demo.chat.service;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface ConversationService {
    String getConversation(User user, Model model) throws JsonProcessingException;
    ResponseEntity<List<Conversation>> getAllConversations(User user);
}
