package com.demo.chat.service.impl;

import com.demo.chat.model.User;
import com.demo.chat.repo.UserRepo;
import com.demo.chat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ImageServiceImpl imageService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder, ImageServiceImpl imageService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo
                .findByUsername(username)
                .orElseThrow();
    }

    @Override
    public String registerUser(User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            return "auth/registration";
        }
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepo.save(user);
        return "redirect:/login";
    }

    @Override
    public ResponseEntity<?> findUsers(String username) {
        HashMap<Object, Object> data = new HashMap<>();
        List<User> users = userRepo.findByUsernameContainsIgnoreCase(username);
        data.put("users", users);

        List<Object> avatars = new ArrayList<>();
        users.forEach(u -> {
            try {
                avatars.add(imageService.getUserAvatar(u));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });

        data.put("avatars", avatars);
        return ResponseEntity.ok().body(data);
    }
}
