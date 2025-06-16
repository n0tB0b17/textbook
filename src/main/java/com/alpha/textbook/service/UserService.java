package com.alpha.textbook.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.textbook.dto.User.CreateUserRequest;
import com.alpha.textbook.dto.User.UserResponse;
import com.alpha.textbook.exception.UserAlreadyExistsException;
import com.alpha.textbook.repository.UserRepository;
import com.alpha.textbook.domain.User;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int MAX_PAGE_SIZE = 100;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse registerUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new UserAlreadyExistsException(
                    "Given username: " + createUserRequest.getUsername() + " Already exists, try different one");
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

    @Transactional(readOnly = true)
    public Page<UserResponse> getUsers(int page, int size) {
        return getUsers(page, size, "joinedAt", "desc");
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> getUsers(int page, int size, String sortBy, String sortDirection) {
        logger.debug("Running getUser function");
        int validatePage = Math.max(0, page);
        int validateSize = Math.min(Math.max(1, size), MAX_PAGE_SIZE);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(validatePage, validateSize, Sort.by(direction, validateSortField(sortBy)));
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::mapUserToResponse);
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> searchUserByUsername(String name, int page, int size) {
        int validatePage = Math.max(0, page);
        int validateSize = Math.min(Math.max(1, size), MAX_PAGE_SIZE);

        Pageable pageable = PageRequest.of(validatePage, validateSize, Sort.by(Sort.Direction.ASC, "username"));
        Page<User> user = userRepository.findByUsernameContainingIgnoreCase(name, pageable);
        return user.map(this::mapUserToResponse);
    }

    private String validateSortField(String sortBy) {
        Set<String> allowedSortFields = Set.of(
                "username", "email", "contactNumber", "joinedAt", "lastLoginAt");
        return allowedSortFields.contains(sortBy) ? sortBy : "joinedAt";
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
