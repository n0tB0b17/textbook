package com.alpha.textbook.dto.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is requred")
    private String password;

    private String email;
    private String contactNumber;
    private String address;
    private String avatarURL;
}
