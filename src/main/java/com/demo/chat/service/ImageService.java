package com.demo.chat.service;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface ImageService {
    List<HashMap<Object, Object>> getAvatars(Conversation conversation) throws IOException;
    Object getAllConversationsData(User user);
    String getPath();
}
