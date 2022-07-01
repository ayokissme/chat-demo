package com.demo.chat.controller;

import com.demo.chat.model.User;
import com.demo.chat.service.impl.SettingsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/settings")
@Slf4j
public class SettingsController {

    private final SettingsServiceImpl settingsService;

    @Autowired
    public SettingsController(SettingsServiceImpl settingsService) {
        this.settingsService = settingsService;
    }

    @PostMapping(value = "/upload/image")
    public ResponseEntity<?> uploadImage(
            @RequestPart MultipartFile imageFile,
            @AuthenticationPrincipal User user
            ) {
        return settingsService.uploadImage(user, imageFile);
    }
}
