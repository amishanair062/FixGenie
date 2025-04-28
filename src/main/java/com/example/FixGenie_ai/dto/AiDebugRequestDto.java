package com.example.FixGenie_ai.dto;

public class AiDebugRequestDto {
    private String errorText;
    private String language;
    private String username;

    public AiDebugRequestDto() {}

    public AiDebugRequestDto(String errorText, String language) {
        this.errorText = errorText;
        this.language = language;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
