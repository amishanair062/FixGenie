package com.example.FixGenie_ai.repository;

import com.example.FixGenie_ai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
	User findByUsername(String username);
	Optional<User> findByResetToken(String resetToken);

}


