package com.demo.chat.service;

import com.demo.chat.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SettingsService {
    ResponseEntity<?> uploadImage(User user, MultipartFile imageFile);
}
