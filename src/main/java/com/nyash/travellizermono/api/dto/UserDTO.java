package com.nyash.travellizermono.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nyash.travellizermono.api.entity.user.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    @NonNull
    Long id;

    @NonNull
    String userName;

    @NonNull
    String password;

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    @JsonProperty("created_at")
    @NonNull
    LocalDateTime createdAt;

//    @NonNull
//    String registrationIp;

    @NonNull
    UserRole userRole;
}
