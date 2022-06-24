package com.demo.chat.service.impl;

import com.demo.chat.model.User;
import com.demo.chat.repo.UserRepo;
import com.demo.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
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
}
