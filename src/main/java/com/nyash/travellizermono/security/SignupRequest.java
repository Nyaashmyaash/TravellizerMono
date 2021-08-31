package com.nyash.travellizermono.security;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {

    String userName;

    String password;

    String firstName;

    String lastName;

    String email;

    Set<String> roles;
}
