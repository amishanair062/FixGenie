package com.example.FixGenie_ai.service;

import com.example.FixGenie_ai.model.User;
import com.example.FixGenie_ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service 
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Spring Security will call this method to load user details
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // username/email
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                /*System.out.println("User Role from DB: " + user.getRole()),*/

                
                
        );
        
        

    }
    
}
