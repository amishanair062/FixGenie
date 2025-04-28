package com.example.FixGenie_ai.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FixGenie_ai.dto.DebugResponseDto;
import com.example.FixGenie_ai.dto.DebugSaveRequestDto;
import com.example.FixGenie_ai.model.DebugSession;
import com.example.FixGenie_ai.model.User;
import com.example.FixGenie_ai.repository.DebugSessionRepository;
import com.example.FixGenie_ai.repository.UserRepository;

@Service
public class DebugServiceImpl implements DebugService {

    @Autowired
    private DebugSessionRepository debugSessionRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Save debug result from frontend
    @Override
    public DebugResponseDto saveDebugSession(DebugSaveRequestDto requestDto, String username) {
        DebugResponseDto responseDto = new DebugResponseDto();

        try {
            User user = findUserByPrincipal(username);

            DebugSession session = new DebugSession();
            session.setUser(user);
            session.setUsername(username);
            session.setCodeInput(requestDto.getCodeInput());
            session.setLanguage(requestDto.getLanguage());
            session.setFixedCode(requestDto.getFixedCode());
            session.setTimestamp(LocalDateTime.now());
            session.setStatus("SUCCESS");
            session.setErrorContext("None");

            debugSessionRepository.save(session);

            responseDto.setStatus("SUCCESS");
            responseDto.setFixedCode(requestDto.getFixedCode());
            responseDto.setMessage("Debug session saved successfully.");
            

        } catch (Exception e) {
            responseDto.setStatus("FAILED");
            responseDto.setMessage("Save failed: " + e.getMessage());
            
            e.printStackTrace();
        }

        return responseDto;
    }

    // ✅ Fetch user history
    @Override
    public List<DebugSession> getUserHistory(String username) {
        return debugSessionRepository.findByUsername(username);
    }

    // 🔍 Utility method for reusable user lookup
    private User findUserByPrincipal(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = userRepository.findByEmail(username).orElse(null);
        }

        if (user == null) {
            throw new RuntimeException("User not found for: " + username);
        }

        return user;
    }
    
    @Override
    public List<DebugSession> getRecentSessions(String email) {
        return debugSessionRepository.findTop5ByUserEmailOrderByTimestampDesc(email);
    }

}
