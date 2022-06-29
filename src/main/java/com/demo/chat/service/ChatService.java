package com.demo.chat.service;

import com.demo.chat.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface ChatService {
    String getChatRoom(User user, Model model) throws JsonProcessingException;
    ModelAndView getChatList(User user);
}
