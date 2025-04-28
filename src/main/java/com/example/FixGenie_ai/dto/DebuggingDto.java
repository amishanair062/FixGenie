package com.example.FixGenie_ai.dto;

public class DebuggingDto {

	private String codeInput;
	private String language;     
	private String errorContext;
	private String fixedCode;    
	private String status;       

	public DebuggingDto() {}

	public DebuggingDto(String codeInput, String language, String errorContext) {
		this.codeInput = codeInput;
		this.language = language;
		this.errorContext = errorContext;
	}

	// Getters & Setters

	public String getCodeInput() {
		return codeInput;
	}

	public void setCodeInput(String codeInput) {
		this.codeInput = codeInput;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getErrorContext() {
		return errorContext;
	}

	public void setErrorContext(String errorContext) {
		this.errorContext = errorContext;
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
}
