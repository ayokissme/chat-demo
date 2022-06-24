package com.demo.chat.repo;

import com.demo.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
