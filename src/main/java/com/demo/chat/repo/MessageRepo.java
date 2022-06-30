package com.demo.chat.repo;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findAllByConversation(Conversation conversation);
}
