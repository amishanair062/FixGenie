package com.example.FixGenie_ai.service;

import com.example.FixGenie_ai.dto.AiDebugRequestDto;
import com.example.FixGenie_ai.dto.AiDebugResponseDto;

public interface AiDebugService {
    AiDebugResponseDto generateDebugSolution(AiDebugRequestDto request);
}
