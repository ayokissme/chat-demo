package com.demo.chat.api;

import com.demo.chat.model.Conversation;
import com.demo.chat.model.User;
import com.demo.chat.model.request.ConversationRequest;
import com.demo.chat.service.impl.ConversationServiceImpl;
import com.demo.chat.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversation")
public class ConversationApi {

    private final ConversationServiceImpl conversationService;
    private final ImageServiceImpl imageService;

    @Autowired
    public ConversationApi(ConversationServiceImpl conversationService, ImageServiceImpl imageService) {
        this.conversationService = conversationService;
        this.imageService = imageService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> allConversation(@AuthenticationPrincipal User user) {
        return conversationService.getAllConversations(user);
    }

    @GetMapping("/get/{me}/{otherUser}")
    public ResponseEntity<?> getConversationAddress(
            @PathVariable("me") User me,
            @PathVariable("otherUser") User otherUser
    ) {
        return conversationService.getConversationAddress(me, otherUser);
    }

    @GetMapping("/get/default/image")
    public ResponseEntity<?> getDefaultImage() {
        try {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("img", imageService.getEncodedImage());
            return ResponseEntity.ok().body(hashMap);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create/new")
    public ResponseEntity<?> createNewConversation(
            @RequestBody ConversationRequest conversationRequest,
            @AuthenticationPrincipal User user
    ) throws IOException {
        return conversationService.createNewConversation(conversationRequest, user);
    }

    @GetMapping("/{convId}/{secKey}")
    public ResponseEntity<?> addUserToChat(
            @AuthenticationPrincipal User user,
            @PathVariable("convId") Conversation conversation,
            @PathVariable("secKey") UUID securityKey
    ) {
        return conversationService.addUserToChatGroup(user, conversation, securityKey);
    }
}
