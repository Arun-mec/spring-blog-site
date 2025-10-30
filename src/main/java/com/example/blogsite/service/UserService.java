package com.example.blogsite.service;

import com.example.blogsite.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(UUID id);
}
