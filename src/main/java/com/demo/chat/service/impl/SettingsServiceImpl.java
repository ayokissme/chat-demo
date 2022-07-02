package com.demo.chat.service.impl;

import com.demo.chat.model.User;
import com.demo.chat.repo.UserRepo;
import com.demo.chat.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final UserRepo userRepo;
    private final ImageServiceImpl imageService;

    @Autowired
    public SettingsServiceImpl(UserRepo userRepo, ImageServiceImpl imageService) {
        this.userRepo = userRepo;
        this.imageService = imageService;
    }

    @Override
    public ResponseEntity<?> uploadImage(User user, MultipartFile imageFile) {
        try {
            byte[] bytes = imageFile.getBytes();
            String image = user.getUsername() + "_avatar.jpg";
            Path path = Paths.get(imageService.getPath() + image);
            Files.write(path, bytes);
            user.setImage(image);
            userRepo.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> getUserImage(User user) {
        try {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("img", imageService.getEncodedImage(user));
            return ResponseEntity.ok().body(hashMap);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
