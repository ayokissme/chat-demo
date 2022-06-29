package com.demo.chat.repo;

import com.demo.chat.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {

    @Query("select c from Chat c where c.senderId =:id")
    List<Chat> findAllChats(@Param("id") UUID id);
}
