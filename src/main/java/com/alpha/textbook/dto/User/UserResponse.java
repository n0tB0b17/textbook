package com.alpha.textbook.dto.User;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String email;
    private String contactNumber;
    private String address;
    private String avatarURL;
    private LocalDateTime joinedAt;
    private LocalDateTime lastLoginAt;
}
