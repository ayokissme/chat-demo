package com.demo.chat.service.impl;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.Message;
import com.demo.chat.model.User;
import com.demo.chat.model.request.MessageRequest;
import com.demo.chat.repo.ConversationRepo;
import com.demo.chat.repo.MessageRepo;
import com.demo.chat.repo.UserRepo;
import com.demo.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.demo.chat.model.enums.MessageStatus.DELIVERED;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;
    private final ConversationRepo conversationRepo;
    private final UserRepo userRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo, ConversationRepo conversationRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.conversationRepo = conversationRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<List<Message>> getMessages(Long convId, User sender) {
        Optional<Conversation> convOptional = conversationRepo.findById(convId);
        if (convOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Conversation conversation = convOptional.get();
        List<Message> messages = messageRepo.findAllByConversation(conversation);
        return ResponseEntity.ok().body(messages);
    }

    @Override
    public ResponseEntity<Message> saveMessage(MessageRequest messageRequest, User sender) throws Exception {
        Message message = new Message();
        message.setSender(sender);
        message.setContent(messageRequest.getContent());
        message.setCreatedAt(LocalDateTime.now());
        message.setStatus(DELIVERED);
//        messageRepo.save(message);
        return ResponseEntity.ok().body(message);
    }
}
