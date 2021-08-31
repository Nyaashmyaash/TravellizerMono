package com.nyash.travellizermono.security;

import com.nyash.travellizermono.api.entity.user.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {

    String token;


    String type = "Bearer";

    Long id;

    String username;

    String email;

    List<Role> roles;

    public JwtResponse(String token, Long id, String username, String email, List<Role> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
