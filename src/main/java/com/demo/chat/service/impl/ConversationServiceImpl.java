package com.demo.chat.service.impl;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.User;
import com.demo.chat.model.Views;
import com.demo.chat.model.request.ConversationRequest;
import com.demo.chat.repo.ConversationRepo;
import com.demo.chat.service.ConversationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.demo.chat.model.enums.ConversationType.*;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService {

    private final ImageServiceImpl imageService;
    private final ConversationRepo conversationRepo;

    @Autowired
    public ConversationServiceImpl(ImageServiceImpl imageService, ConversationRepo conversationRepo) {
        this.imageService = imageService;
        this.conversationRepo = conversationRepo;
    }

    @Override
    public String getConversation(User user, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper
                .writerWithView(Views.NoPassword.class)
                .writeValueAsString(user);

        Map<Object, Object> frontendData = new HashMap<>();
        frontendData.put("user", result);
        model.addAttribute("frontendData", frontendData);

        return "/chat/chat";
    }

    @Override
    public ResponseEntity<?> getAllConversations(User user) {
        return ResponseEntity.ok().body(imageService.getAllConversationsData(user));
    }

    @Override
    public ResponseEntity<?> getConversationAddress(User me, User otherUser) {
        Optional<Conversation> conversation = conversationRepo.getConversation(me, otherUser, ONE_TO_ONE);

        if (conversation.isPresent()) {
            return ResponseEntity.ok().body(conversation);
        }
        Conversation newConversation = new Conversation();
        newConversation.addParticipant(me);
        newConversation.addParticipant(otherUser);
        newConversation.setConversationType(ONE_TO_ONE);
        newConversation.setLastMessageCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok().body(conversationRepo.save(newConversation));
    }

    @Override
    public ResponseEntity<?> createNewConversation(ConversationRequest conversationRequest, User user) throws IOException {
        Conversation conversation = new Conversation();
        conversation.addParticipant(user);
        conversation.setConversationType(MULTIPLE);
        conversation.setLastMessageCreatedAt(LocalDateTime.now());
        conversation.setName(conversationRequest.getName());
        conversation.setSecretKey(UUID.randomUUID());
        try {
            conversation = conversationRepo.save(conversation);
            String imgName = conversation.getConversationId() + ".jpg";
            conversation.setImage(imgName);
            imageService.encodeImageToBytesAndSave(
                    imgName,
                    conversationRequest.getImageFile()
            );
            return ResponseEntity.ok().body(conversationRepo.save(conversation));
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public ResponseEntity<?> addUserToChatGroup(User user, Conversation conversation, UUID securityKey) {
        if (!conversation.getSecretKey().equals(securityKey)) {
            return ResponseEntity.badRequest().build();
        }
        if (conversation.isUserInChatGroup(user)) {
            return ResponseEntity.ok().build();
        }
        conversation.addParticipant(user);
        conversationRepo.save(conversation);
        return ResponseEntity.ok().build();
    }
}
