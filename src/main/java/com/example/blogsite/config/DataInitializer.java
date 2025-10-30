package com.example.blogsite.config;

import com.example.blogsite.domain.entity.User;
import com.example.blogsite.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com")==null) {
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .email("admin@gmail.com")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                userRepository.save(user);
            }

            if (userRepository.findByEmail("testuser@gmail.com")==null) {
                User user = User.builder()
                        .username("testuser")
                        .password(passwordEncoder.encode("testuser123"))
                        .email("testuser@gmail.com")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                userRepository.save(user);
            }
        };
    }
}
