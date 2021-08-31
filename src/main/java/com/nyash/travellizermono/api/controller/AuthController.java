package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.repository.RoleRepository;
import com.nyash.travellizermono.api.repository.UserRepository;
import com.nyash.travellizermono.security.LoginRequest;
import com.nyash.travellizermono.security.SignupRequest;
import com.nyash.travellizermono.security.config.jwt.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthController {

    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    JwtUtils jwtUtils;

    public static final String SIGN_IN = "api/auth/signin";
    public static final String SIGN_UP = "api/auth/signup";

    @PostMapping(SIGN_IN)
    public ResponseEntity<?> authUser(
            @Valid @RequestBody LoginRequest loginRequest) {
        return null;
    }

    @PostMapping(SIGN_UP)
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody SignupRequest signupRequest) {
        return null;
    }
}
