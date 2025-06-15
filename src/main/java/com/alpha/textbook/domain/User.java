package com.alpha.textbook.domain;

import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Data;
import lombok.NoArgsConstructor;

@Node("User")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Property("username")
    private String username;

    @Property("password")
    private String password;

    @Property("email")
    private String email;

    @Property("contactNumber")
    private String contactNumber;

    @Property("address")
    private String address;

    @Property("avatarURL")
    private String avatarURL;

    @CreatedDate
    private LocalDateTime joinedAt;

    @LastModifiedDate
    private LocalDateTime lastLoginAt;

    public User(String username, String password, String email, String contactNumber, String address,
            String avatarURL) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
        this.avatarURL = avatarURL;
    }
}
