package com.example.FixGenie_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
@ComponentScan(basePackages = "com.example.FixGenie_ai")
@EnableJpaRepositories(basePackages = "com.example.FixGenie_ai.repository")
@EntityScan(basePackages = "com.example.FixGenie_ai.model")
@EnableAsync
public class FixGenieAiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FixGenieAiApplication.class, args);
    }
}