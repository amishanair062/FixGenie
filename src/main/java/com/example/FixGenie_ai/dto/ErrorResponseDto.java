package com.example.FixGenie_ai.dto;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    private String error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(String error, String message, LocalDateTime timestamp) {
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String error;
        private String message;
        private LocalDateTime timestamp;

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponseDto build() {
            return new ErrorResponseDto(error, message, timestamp);
        }
    }

    // Getters & Setters
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

