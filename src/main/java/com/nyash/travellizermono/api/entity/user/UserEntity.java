package com.nyash.travellizermono.api.entity.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity that encapsulates user of the application
 *
 * @author Nyash
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {  //TODO: Users e-mail integration

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
    String username;

    /**
     * User password
     */
    @NonNull
    @Column(name = "PASSWORD")
    String password;

    //Dont need first and last name now.

//    @NonNull
//    @Column(name = "FIRST_NAME", length = 24)
//    String firstName;
//
//    @NonNull
//    @Column(name = "LAST_NAME")
//    String lastName;

    @NonNull
    @Column(name = "EMAIL")
    String email;

    /**
     * User role
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    Set<Role> roles = new HashSet<>();
}
