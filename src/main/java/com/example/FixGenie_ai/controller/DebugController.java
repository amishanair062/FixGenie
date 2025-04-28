package com.example.FixGenie_ai.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.FixGenie_ai.dto.DebugRequestDto;
import com.example.FixGenie_ai.dto.DebugResponseDto;
import com.example.FixGenie_ai.dto.DebugSaveRequestDto;
import com.example.FixGenie_ai.model.DebugSession;
import com.example.FixGenie_ai.repository.DebugSessionRepository;
import com.example.FixGenie_ai.service.DebugService;


@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @Autowired
    private DebugService debugService;

    @PostMapping("/save")
    public ResponseEntity<DebugResponseDto> saveDebugResult(@RequestBody DebugSaveRequestDto requestDto, Principal principal)
 {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        DebugResponseDto response = debugService.saveDebugSession(requestDto, principal.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-history")
    public ResponseEntity<List<DebugSession>> getMyHistory(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        List<DebugSession> sessions = debugService.getUserHistory(principal.getName());
        return ResponseEntity.ok(sessions);
    }
    
    @GetMapping("/recent")
    public ResponseEntity<List<DebugSession>> getRecentDebugSessions(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        List<DebugSession> recentSessions = debugService.getRecentSessions(principal.getName());
        return ResponseEntity.ok(recentSessions);
    }

}
