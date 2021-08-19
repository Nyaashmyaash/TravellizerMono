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

    @JsonProperty("user_name")
    @NonNull
    String userName;

    @NonNull
    String password;

    @JsonProperty("first_name")
    @NonNull
    String firstName;

    @JsonProperty("last_name")
    @NonNull
    String lastName;

    @JsonProperty("created_at")
    @NonNull
    LocalDateTime createdAt;

//    @NonNull
//    String registrationIp;

    @JsonProperty("user_role")
    @NonNull
    UserRole userRole;
}
