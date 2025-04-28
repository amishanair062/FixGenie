package com.example.FixGenie_ai.service;


import com.example.FixGenie_ai.dto.UserDto;
import com.example.FixGenie_ai.model.User;
import com.example.FixGenie_ai.repository.UserRepository;
import com.example.FixGenie_ai.security.JwtUtil;
import com.example.FixGenie_ai.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
    private  UserRepository userRepository;

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private MailService mailService;
    @Autowired private JwtUtil jwtUtil; 

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String sendResetLink(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found!"));

        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        
        // Set expiration time (e.g., 10 minutes from now)
        user.setResetTokenExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        
        userRepository.save(user);

        String resetLink = "http://localhost:8080/resetpage.html?token=" + resetToken;
        mailService.sendResetLink(email, resetLink);

        return "Reset link sent to " + email;
    }


    @Override
    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired reset token!"));

        if (user.getResetTokenExpiration() == null || user.getResetTokenExpiration().before(new Date())) {
            throw new RuntimeException("Reset token has expired!");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null); // Clear token
        user.setResetTokenExpiration(null); // Clear expiration
        userRepository.save(user);
    }


}
