package com.example.FixGenie_ai.repository;

import com.example.FixGenie_ai.model.DebugSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebugSessionRepository extends JpaRepository<DebugSession, Long> {
    List<DebugSession> findByUsername(String username);
    List<DebugSession> findTop5ByUserEmailOrderByTimestampDesc(String email);

    
}