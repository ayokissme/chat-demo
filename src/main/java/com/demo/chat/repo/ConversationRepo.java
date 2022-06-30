package com.demo.chat.repo;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepo extends JpaRepository<Conversation, Long> {

    @Query("select co from Conversation co " +
            "join co.participants cp " +
            "on cp =:u")
    List<Conversation> findAllConversations(@Param("u") User user);
}
