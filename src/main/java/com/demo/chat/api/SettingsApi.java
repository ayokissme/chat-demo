package com.demo.chat.api;

import com.demo.chat.model.User;
import com.demo.chat.service.impl.SettingsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/settings")
@Slf4j
public class SettingsApi {

    private final SettingsServiceImpl settingsService;

    @Autowired
    public SettingsApi(SettingsServiceImpl settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping("/get/image")
    public ResponseEntity<?> getImage(@AuthenticationPrincipal User user) {
        return settingsService.getUserImage(user);
    }

    @PostMapping("/upload/image")
    public ResponseEntity<?> uploadImage(
            @RequestPart MultipartFile imageFile,
            @AuthenticationPrincipal User user
            ) {
        return settingsService.uploadImage(user, imageFile);
    }
}
