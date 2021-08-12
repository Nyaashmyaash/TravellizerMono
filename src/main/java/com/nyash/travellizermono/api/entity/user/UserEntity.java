package com.nyash.travellizermono.api.entity.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity that encapsulates user of the application
 *
 * @author Nyash
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "USERS")
public class UserEntity {

    /**
     * User unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Unique user name within the system
     */
    @NonNull
    @Column(name = "USER_NAME", unique = true, length = 24)
    String userName;

    /**
     * User password
     */
    @NonNull
    @Column(name = "PASSWORD")
    String password;

    @NonNull
    @Column(name = "FIRST_NAME", length = 24)
    String firstName;

    @NonNull
    @Column(name = "LAST_NAME")
    String lastName;

    /**
     * Timestamp of user registration
     */

    LocalDateTime createdAt;

    /**
     * IP of user registration
     */
    @Column(name = "REGISTRATION_IP", length = 30)
    String registrationIp;

    /**
     * User role
     */
    @Enumerated(EnumType.STRING)
    UserRole userRole;

}
