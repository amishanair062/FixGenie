package com.example.FixGenie_ai.controller;

import com.example.FixGenie_ai.dto.*;
import com.example.FixGenie_ai.model.User;
import com.example.FixGenie_ai.repository.UserRepository;
import com.example.FixGenie_ai.security.JwtUtil;
import com.example.FixGenie_ai.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired UserService userService;
    
    @PostMapping("/signup")
    public AuthResponseDto registerUser(@RequestBody SignupRequestDto signupDto) {
        if (userRepository.findByEmail(signupDto.getEmail()).isPresent()) {
            return AuthResponseDto.builder()
                    .message("Email already exists!")
                    .status(false)
                    .build();
        }

        User user = User.builder()
                .email(signupDto.getEmail())
                .username(signupDto.getUsername())
                .password(passwordEncoder.encode(signupDto.getPassword()))   
                .build();
        
        user.setRole("USER");
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        
        return AuthResponseDto.builder()
                .message("User registered successfully!")
                .status(true)
                .token(token)
                .build();
    }

    @PostMapping("/login")
    public AuthResponseDto loginUser(@RequestBody LoginRequestDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(), loginDto.getPassword())
            );

            User user = userRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loginDto.getEmail()));

            String token = jwtUtil.generateToken(user.getEmail());

            return AuthResponseDto.builder()
                    .token(token)
                    .message("Login successful")
                    .status(true)
                    .username(user.getUsername())
                    .build();

        } catch (BadCredentialsException e) {
            return AuthResponseDto.builder()
                    .message("Invalid email or password")
                    .status(false)
                    .build();
        } catch (Exception e) {
            return AuthResponseDto.builder()
                    .message("Login error: " + e.getMessage())
                    .status(false)
                    .build();
        }
    }
    
 

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        userService.sendResetLink(email);
        return ResponseEntity.ok("Reset link sent successfully!");
    }

    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");
        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully!");
    }


}
