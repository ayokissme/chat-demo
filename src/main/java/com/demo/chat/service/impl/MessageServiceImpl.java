package com.demo.chat.service.impl;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.Message;
import com.demo.chat.model.User;
import com.demo.chat.model.request.MessageRequest;
import com.demo.chat.repo.ConversationRepo;
import com.demo.chat.repo.MessageRepo;
import com.demo.chat.repo.UserRepo;
import com.demo.chat.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.demo.chat.model.enums.MessageStatus.DELIVERED;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;
    private final ConversationRepo conversationRepo;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepo userRepo;

    @Autowired
    public MessageServiceImpl(
            MessageRepo messageRepo,
            ConversationRepo conversationRepo,
            SimpMessagingTemplate simpMessagingTemplate,
            UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.conversationRepo = conversationRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<HashMap<String, Object>> getMessages(Long convId, User sender) {
        Optional<Conversation> convOptional = conversationRepo.findById(convId);
        if (convOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Conversation conversation = convOptional.get();
        boolean isNotInConversation = conversation.getParticipants()
                .stream()
                .map(User::getId)
                .noneMatch(uuid -> uuid.equals(sender.getId()));
        if (isNotInConversation) {
            return ResponseEntity.badRequest().build();
        }

        HashMap<String, Object> response = new HashMap<>();
        List<Message> messages = messageRepo.findAllByConversation(conversation);
        response.put("conversation", conversation);
        response.put("messages", messages);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public Message saveMessage(Message message, String username) {
        User sender = userRepo.findByUsername(username).orElseGet(User::new);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        message.setStatus(DELIVERED);
        return messageRepo.save(message);
    }

    @Override
    public void sendMessageToUsers(MessageRequest messageRequest, Principal principal) throws Exception {
        Message message = new Message();
        BeanUtils.copyProperties(messageRequest, message);
        Set<User> participants = messageRequest.getConversation().getParticipants();
//        List<String> participants = messageRequest.getConversation().getParticipants()
//                .stream()
//                .map(User::getUsername)
//                .filter(username -> !username.equals(principal.getName()))
//                .collect(Collectors.toList());

        participants.forEach(p ->
                simpMessagingTemplate.convertAndSendToUser(
                        p.getUsername(),
                        "/queue/reply",
                        saveMessage(message, principal.getName())
                )
        );
    }
}
