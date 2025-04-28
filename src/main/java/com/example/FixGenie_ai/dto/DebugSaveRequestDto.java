package com.example.FixGenie_ai.dto;

public class DebugSaveRequestDto {

	private String codeInput;
	private String language;
	private String fixedCode;
	
	
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
	public String getFixedCode() {
		return fixedCode;
	}
	public void setFixedCode(String fixedCode) {
		this.fixedCode = fixedCode;
	}
	
	
}
