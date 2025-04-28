package com.example.FixGenie_ai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private String resetToken;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date resetTokenExpiration;  // ⏰ Added new field

    // --- Getters and Setters ---

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getResetTokenExpiration() {
        return resetTokenExpiration;
    }

    public void setResetTokenExpiration(Date resetTokenExpiration) {
        this.resetTokenExpiration = resetTokenExpiration;
    }

    // --- Builder ---
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String email;
        private String username;
        private String password;
        private String role;
        private String resetToken;
        private Date resetTokenExpiration; // Add in builder too

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UserBuilder resetToken(String resetToken) {
            this.resetToken = resetToken;
            return this;
        }

        public UserBuilder resetTokenExpiration(Date resetTokenExpiration) {
            this.resetTokenExpiration = resetTokenExpiration;
            return this;
        }

        public User build() {
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            user.setResetToken(resetToken);
            user.setResetTokenExpiration(resetTokenExpiration);
            return user;
        }
    }
}
