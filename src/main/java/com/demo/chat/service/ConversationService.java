package com.demo.chat.service;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.User;
import com.demo.chat.model.request.ConversationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.UUID;

public interface ConversationService {
    String getConversation(User user, Model model) throws JsonProcessingException;
    ResponseEntity<?> getAllConversations(User user);
    ResponseEntity<?> getConversationAddress(User me, User otherUser);
    ResponseEntity<?> createNewConversation(ConversationRequest conversationRequest, User user) throws IOException;
    ResponseEntity<?> addUserToChatGroup(User user, Conversation conversation, UUID securityKey);
}
