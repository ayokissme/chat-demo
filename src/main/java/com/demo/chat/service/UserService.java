package com.demo.chat.service;

import com.demo.chat.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    String registerUser(User user);
    ResponseEntity<?> findUsers(String username);
}
