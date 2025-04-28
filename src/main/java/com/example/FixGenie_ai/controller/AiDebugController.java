package com.example.FixGenie_ai.controller;

import com.example.FixGenie_ai.dto.AiDebugRequestDto;
import com.example.FixGenie_ai.dto.AiDebugResponseDto;
import com.example.FixGenie_ai.service.AiDebugService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/debug")
@CrossOrigin(origins= "http://localhost:8080", allowCredentials = "true")
public class AiDebugController {

    @Autowired
    private AiDebugService aiDebugService;

    @PostMapping("/ai-solve")
    public AiDebugResponseDto solveWithAi(@RequestBody AiDebugRequestDto request) {
        return aiDebugService.generateDebugSolution(request);
    }
    
   
}
