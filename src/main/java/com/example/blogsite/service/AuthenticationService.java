package com.example.blogsite.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails authenticateUser(String email, String password);

    String generateToken(UserDetails userDetails);
}
