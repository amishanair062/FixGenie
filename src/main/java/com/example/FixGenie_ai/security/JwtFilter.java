package com.example.FixGenie_ai.security;

import com.example.FixGenie_ai.service.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;


    @Autowired
    private JwtUtil jwtUtil;
    
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
		return path.startsWith("/auth"); 	/* 👈 This skips all /auth routes from filtering */
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // ✅ Extract token and username first
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7); // Remove "Bearer "
            username = jwtUtil.extractUsername(jwt); // Get email/username from token
        }

        // ✅ Debug logs AFTER extraction
        System.out.println("Authorization Header: " + authHeader);
        System.out.println("JWT: " + jwt);
        System.out.println("Username from JWT: " + username);
        System.out.println("SecurityContext Authentication: " + SecurityContextHolder.getContext().getAuthentication());

        // ✅ Validate token only after extraction
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(jwt, username)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
