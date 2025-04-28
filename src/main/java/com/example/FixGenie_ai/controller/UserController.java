package com.example.FixGenie_ai.controller;

import com.example.FixGenie_ai.dto.UserDto;
import com.example.FixGenie_ai.model.User;
import com.example.FixGenie_ai.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping
   // @PreAuthorize("hasRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers().stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();

        UserDto dto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();

        return ResponseEntity.ok(dto);
    }
    
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfile(java.security.Principal principal) {
        User user = userService.findByEmail(principal.getName());

        UserDto dto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();

        return ResponseEntity.ok(dto);
    }

    

    @DeleteMapping("/{id}")
   // @PreAuthorize("hasRole('ADMIN', 'USER')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    
    
    
}
