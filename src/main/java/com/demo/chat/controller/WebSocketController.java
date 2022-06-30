package com.demo.chat.controller;

import com.demo.chat.model.Message;
import com.demo.chat.model.User;
import com.demo.chat.model.request.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/queue/reply")
    public void send(
            @Payload MessageRequest messageRequest,
            Principal user,
            @Header("simpSessionId") String sessionId
    ) {
        log.info("Message: {}", messageRequest.getContent());
        Message message = new Message();
        BeanUtils.copyProperties(messageRequest, message);
//        message.setSender(user);
        List<String> participants = messageRequest.getConversation().getParticipants()
                .stream()
                .map(User::getUsername)
                .filter(username -> !username.equals(user.getName()))
                .collect(Collectors.toList());
        participants.forEach(p -> {
            simpMessagingTemplate.convertAndSendToUser(p, "/queue/reply", message);
        });
    }
}
