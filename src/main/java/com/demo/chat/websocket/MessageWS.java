package com.demo.chat.websocket;

import com.demo.chat.model.Message;
import com.demo.chat.model.request.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@Slf4j
public class MessageWS {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public MessageWS(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/send")
    public void send(MessageRequest message,
                     Principal user,
                     @Header("simpSessionId") String sessionId
    ) {
        log.info("Message: {}", message.getContent());
        simpMessagingTemplate.convertAndSendToUser("b", "", new Message());
    }
}
