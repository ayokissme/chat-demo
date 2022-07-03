package com.demo.chat.repo;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.User;
import com.demo.chat.model.enums.ConversationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ConversationRepo extends JpaRepository<Conversation, Long> {

    @Query("select co from Conversation co " +
            "join co.participants cp " +
            "on cp =:u")
    List<Conversation> findAllConversations(@Param("u") User user);

    @Query(
            "select co from Conversation co " +
                    "join co.participants cp1 on cp1 =:me " +
                    "join co.participants cp2 on cp2 =:ou " +
                    "where co.conversationType =:t "
    )
    Optional<Conversation> getConversation(@Param("me") User me, @Param("ou") User otherUser, @Param("t") ConversationType type);
}
