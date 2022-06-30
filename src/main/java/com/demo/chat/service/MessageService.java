package com.demo.chat.service;

import com.demo.chat.model.Message;
import com.demo.chat.model.User;
import com.demo.chat.model.request.MessageRequest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface MessageService {
    ResponseEntity<HashMap<String, Object>> getMessages(Long recipientId, User sender);
    ResponseEntity<Message> saveMessage(MessageRequest message, User sender) throws Exception;
}
