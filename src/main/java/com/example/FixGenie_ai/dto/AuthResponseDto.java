package com.example.FixGenie_ai.dto;

public class AuthResponseDto {
    private String token;
    private String message;
    private boolean status;
    private String username;

    public AuthResponseDto() {
    }

    public AuthResponseDto(String token, String message, boolean status, String username) {
        this.token = token;
        this.message = message;
        this.status = status;
        this.username = username;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private String message;
		private boolean status;
		private String username;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public AuthResponseDto build() {
            return new AuthResponseDto(token, message, status, username);
        }

        public Builder status(boolean status) {
            this.status = status;
            return this;
        }
        public Builder username(String username) {
            this.username = username;
            return this;
        }

    }

    // Getters & Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    } 

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}

