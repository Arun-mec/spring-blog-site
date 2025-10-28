package com.example.blogsite.utils;

import com.example.blogsite.security.BlogUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretString;
    private Long expiration = 1000*60*60L;

    public String getJwtToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSecretKey(secretString))
                .compact();
    }

    public SecretKey getSecretKey(String secretString) {
        return Keys.hmacShaKeyFor(secretString.getBytes());
    }

    public String getUsername(String jwtToken) {
        return getClaims(jwtToken).getSubject();
    }

    public boolean validateToken(BlogUserDetails blogUserDetails, String username, String token) {
        return (username.equals(blogUserDetails.getUsername()) && !validateExpiration(token));
    }

    protected boolean validateExpiration(String token) {
        return getClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    protected Claims getClaims(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(getSecretKey(secretString))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
