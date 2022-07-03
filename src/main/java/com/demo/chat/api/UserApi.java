package com.demo.chat.api;

import com.demo.chat.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserServiceImpl userService;

    @Autowired
    public UserApi(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<?> findUser(@PathVariable("username") String username) {
        return userService.findUsers(username);
    }
}
