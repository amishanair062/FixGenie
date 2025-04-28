package com.example.FixGenie_ai.dto;

public class DebugRequestDto {
    private String codeInput;
    private String language;

    // Getters and Setters
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
}
