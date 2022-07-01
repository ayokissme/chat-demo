package com.demo.chat.service.impl;

import com.demo.chat.model.User;
import com.demo.chat.repo.UserRepo;
import com.demo.chat.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final UserRepo userRepo;

    @Value("${folder.images.profile.pic}")
    private String folder;
    public static String absolutePath = Paths.get(".").toAbsolutePath().normalize().toString().replace("\\", "/");

    @Autowired
    public SettingsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<?> uploadImage(User user, MultipartFile imageFile) {
        byte[] bytes;
        try {
            bytes = imageFile.getBytes();
            String photo = user.getUsername() + "_avatar.jpg";
            Path path = Paths.get(absolutePath + folder + photo);
            Files.write(path, bytes);
            user.setPhoto(photo);
            userRepo.save(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("imageFile", bytes);
        return ResponseEntity.ok().body(hashMap);
    }
}
