package com.alpha.textbook.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alpha.textbook.dto.User.CreateUserRequest;
import com.alpha.textbook.dto.User.UserResponse;
import com.alpha.textbook.exception.UserAlreadyExistsException;
import com.alpha.textbook.repository.UserRepository;
import com.alpha.textbook.domain.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse registerUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new UserAlreadyExistsException(null);
        }

        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setEmail(createUserRequest.getEmail());
        user.setContactNumber(createUserRequest.getContactNumber());
        user.setAddress(createUserRequest.getAddress());
        user.setAvatarURL(createUserRequest.getAvatarURL());
        user.setJoinedAt(LocalDateTime.now());
        user.setLastLoginAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return mapUserToResponse(savedUser);
    }

    private UserResponse mapUserToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setContactNumber(user.getContactNumber());
        userResponse.setAddress(user.getAddress());
        userResponse.setAvatarURL(user.getAvatarURL());
        userResponse.setJoinedAt(user.getJoinedAt());
        userResponse.setLastLoginAt(user.getLastLoginAt());

        return userResponse;
    }
}
