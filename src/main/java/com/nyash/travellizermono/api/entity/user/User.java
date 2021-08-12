package com.nyash.travellizermono.api.entity.user;

import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity that encapsulates user of the application
 *
 * @author Nyash
 *
 */
@Setter
public class User {

    private UUID id;

    /**
     * Unique user name within the system
     */
    private String userName;

    /**
     * User password
     */
    private String password;

    private String firstName;

    private String lastName;

    /**
     * Timestamp of user registration
     */
    private LocalDateTime createdAt;

    /**
     * IP of user registration
     */
    private String registrationIp;

    /**
     * User role
     */
    private Role role;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getRegistrationIp() {
        return registrationIp;
    }

    public Role getRole() {
        return role;
    }
}
