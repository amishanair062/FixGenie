package com.example.FixGenie_ai.dto;

public class SignupRequestDto {
    private String username;
    private String email;
    private String password;

    public SignupRequestDto() {
    }

    public SignupRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public SignupRequestDto build() {
            return new SignupRequestDto(username, email, password);
        }
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private String role;

    public String getRole() {
        return role;
    }

}
