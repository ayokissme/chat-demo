package com.demo.chat.service;

import com.demo.chat.model.Message;
import com.demo.chat.model.User;
import com.demo.chat.model.request.MessageRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.HashMap;

public interface MessageService {
    ResponseEntity<HashMap<String, Object>> getMessages(Long recipientId, User sender);
    Message saveMessage(Message message, String username) throws Exception;
    void sendMessageToUsers(MessageRequest messageRequest, Principal user) throws Exception;
}
