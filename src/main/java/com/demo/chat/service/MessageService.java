package com.demo.chat.service;

import com.demo.chat.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();
}
