package com.alpha.textbook.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.textbook.dto.User.CreateUserRequest;
import com.alpha.textbook.dto.User.UserResponse;
import com.alpha.textbook.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        logger.info("registering in user: {} to our server", createUserRequest.getUsername());
        UserResponse userResponse = userService.registerUser(createUserRequest);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "joinedAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection) {
        logger.debug("Fetching users - page: {}, size: {}, sortBy: {}, sortDirection: {}",
                page, size, sortBy, sortDirection);

        Page<UserResponse> userResponses = userService.getUsers(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserResponse>> searchUserByUsername(
            @RequestParam String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.debug("Searching users by username: {} - page: {}, size: {}", username, page, size);

        Page<UserResponse> userResponse = userService.searchUserByUsername(username, page, size);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/joined-date")
    public ResponseEntity<Page<UserResponse>> searchUserByJoinedDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        logger.debug("Fetching users by date range: {} to {} - page: {}, size: {}",
                startDate, endDate, page, size);
        Page<UserResponse> userResponse = userService.getUsersByJoinedDateRange(startDate, endDate, page, size);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/recent-activity")
    public ResponseEntity<Page<UserResponse>> searchUserByRecentActivity(
            @RequestParam(defaultValue = "30") int days,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
                
        logger.debug("Fetching recently active users (last {} days) - page: {}, size: {}",
                days, page, size);
        Page<UserResponse> userResponse = userService.getRecentlyActiveUser(days, page, size);
        return ResponseEntity.ok(userResponse);
    }
}
