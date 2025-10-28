package com.example.blogsite.filter;

import com.example.blogsite.domain.entity.User;
import com.example.blogsite.repository.UserRepository;
import com.example.blogsite.security.BlogUserDetails;
import com.example.blogsite.security.BlogUserDetailsService;
import com.example.blogsite.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    BlogUserDetailsService blogUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null, username = null;

        if (authHeader!=null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            username = jwtUtil.getUsername(jwtToken);
        }

        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            BlogUserDetails blogUserDetails = blogUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(blogUserDetails, username, jwtToken)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        blogUserDetails, null, blogUserDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
