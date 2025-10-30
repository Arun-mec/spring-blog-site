package com.example.blogsite.controller;

import com.example.blogsite.domain.dto.UserDto;
import com.example.blogsite.domain.dto.UserRequest;
import com.example.blogsite.domain.dto.UserRequestDto;
import com.example.blogsite.domain.entity.User;
import com.example.blogsite.mapper.UserMapper;
import com.example.blogsite.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        List<UserDto> users = usersList.stream().map(userMapper::toDto).toList();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserRequest userRequest = userMapper.toUserRequest(userRequestDto);
        User newUser = userService.createUser(userRequest);
        UserDto user = userMapper.toDto(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
