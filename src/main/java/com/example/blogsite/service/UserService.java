package com.example.blogsite.service;

import com.example.blogsite.domain.dto.UserRequest;
import com.example.blogsite.domain.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(UUID id);

    User createUser(UserRequest userRequest);

    void deleteUser(UUID id);
}
