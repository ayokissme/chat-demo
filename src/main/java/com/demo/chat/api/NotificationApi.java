package com.demo.chat.api;

import com.demo.chat.model.User;
import com.demo.chat.service.impl.ImageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/notification")
@Slf4j
public class NotificationApi {

    private final ImageServiceImpl imageService;

    @Autowired
    public NotificationApi(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/get/avatar/{userId}")
    public ResponseEntity<?> getAvatar(@PathVariable("userId") User user) {
        try {
            return ResponseEntity.ok().body(imageService.getUserAvatar(user));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
