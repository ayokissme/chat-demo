package com.demo.chat.service;

import com.demo.chat.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ui.Model;

public interface ChatService {
    String listChats(User user);
    String getChatRoom(User user, Model model) throws JsonProcessingException;
}
