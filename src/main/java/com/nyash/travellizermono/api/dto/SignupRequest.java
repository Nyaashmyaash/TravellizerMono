package com.nyash.travellizermono.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {

    String userName;

    String password;

    String firstName;

    String lastName;

    String email;

    Set<String> roles;
}
