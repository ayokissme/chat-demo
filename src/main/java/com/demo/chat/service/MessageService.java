package com.demo.chat.service;

import com.demo.chat.model.Message;
import com.demo.chat.model.User;
import com.demo.chat.model.request.MessageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();
    ResponseEntity<?> saveMessage(MessageRequest message, User sender) throws Exception;
}
