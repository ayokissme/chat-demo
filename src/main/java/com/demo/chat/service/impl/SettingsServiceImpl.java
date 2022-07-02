package com.demo.chat.service.impl;

import com.demo.chat.model.User;
import com.demo.chat.repo.UserRepo;
import com.demo.chat.service.SettingsService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
            String image = user.getUsername() + "_avatar.jpg";
            Path path = Paths.get(absolutePath + folder + image);
            Files.write(path, bytes);
            user.setImage(image);
            userRepo.save(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> getUserImage(User user) {
        try {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("img", encodeImage(user));
            return ResponseEntity.ok().body(hashMap);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public String encodeImage(User user) throws IOException {
        String image = absolutePath + folder + user.getImage();
        byte[] fileContent = FileUtils.readFileToByteArray(new File(image));
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
