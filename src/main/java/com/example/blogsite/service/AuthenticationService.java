package com.example.blogsite.service;

import com.example.blogsite.security.BlogUserDetails;

public interface AuthenticationService {

    BlogUserDetails authenticateUser(String email, String password);

    String generateToken(BlogUserDetails userDetails);
}
