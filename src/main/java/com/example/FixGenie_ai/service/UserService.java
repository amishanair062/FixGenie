package com.example.FixGenie_ai.service;

import com.example.FixGenie_ai.dto.UserDto;
import com.example.FixGenie_ai.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserByEmail(String email);
    void deleteUser(Long id);
    User findByEmail(String email);
    public String sendResetLink(String email);
    void resetPassword(String token, String newPassword);

    

}

