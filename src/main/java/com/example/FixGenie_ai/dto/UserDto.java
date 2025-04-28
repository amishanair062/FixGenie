package com.example.FixGenie_ai.dto;

public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String role;
    private String resetToken;

    public UserDto() {
    }

    public UserDto(Long id, String email, String username, String role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String email;
        private String username;
        private String role;
        private String resetToken;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }
        
        public Builder resetToken(String resetToken) {
            this.resetToken = resetToken;
            return this;
        }

        public UserDto build() {
            return new UserDto(id, email, username, role);
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
    
    
}
