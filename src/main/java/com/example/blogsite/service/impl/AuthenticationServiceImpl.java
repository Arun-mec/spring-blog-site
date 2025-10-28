package com.example.blogsite.service.impl;

import com.example.blogsite.domain.entity.User;
import com.example.blogsite.security.BlogUserDetails;
import com.example.blogsite.security.BlogUserDetailsService;
import com.example.blogsite.service.AuthenticationService;
import com.example.blogsite.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final BlogUserDetailsService blogUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public BlogUserDetails authenticateUser(String email, String password) throws UsernameNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return (BlogUserDetails) blogUserDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(BlogUserDetails userDetails) {
        return jwtUtil.getJwtToken(userDetails);
    }
}
