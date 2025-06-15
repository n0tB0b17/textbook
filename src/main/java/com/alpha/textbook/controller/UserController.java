package com.alpha.textbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.textbook.dto.User.CreateUserRequest;
import com.alpha.textbook.dto.User.UserResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        logger.debug("registering in user: {} to our server", createUserRequest.getUsername());
        return null;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return null;
    }
}
