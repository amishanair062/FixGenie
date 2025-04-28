package com.example.FixGenie_ai.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "debug_session")
public class DebugSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "user_id")
	 private User user;
	 


	private String username;

	@Column(length = 20000)
	private String codeInput;

	@Column(length = 20000)
	private String fixedCode;

	private String status;

	private String language;

	private LocalDateTime timestamp;

	private String errorContext;

	// Getters and Setters
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCodeInput() {
		return codeInput;
	}

	public void setCodeInput(String codeInput) {
		this.codeInput = codeInput;
	}

	public String getFixedCode() {
		return fixedCode;
	}

	public void setFixedCode(String fixedCode) {
		this.fixedCode = fixedCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorContext() {
		return errorContext;
	}

	public void setErrorContext(String errorContext) {
		this.errorContext = errorContext;
	}
	
	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}
}
