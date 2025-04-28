package com.example.FixGenie_ai.service;

import java.security.Principal;
import java.util.List;

import com.example.FixGenie_ai.dto.DebugRequestDto;
import com.example.FixGenie_ai.dto.DebugResponseDto;
import com.example.FixGenie_ai.dto.DebugSaveRequestDto;
import com.example.FixGenie_ai.model.DebugSession;

public interface DebugService {

	DebugResponseDto saveDebugSession(DebugSaveRequestDto requestDto, String username);

	    List<DebugSession> getUserHistory(String username);
	    List<DebugSession> getRecentSessions(String email);

}
