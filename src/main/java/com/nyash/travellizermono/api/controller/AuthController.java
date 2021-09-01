package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.entity.user.ERole;
import com.nyash.travellizermono.api.entity.user.Role;
import com.nyash.travellizermono.api.entity.user.UserEntity;
import com.nyash.travellizermono.api.repository.RoleRepository;
import com.nyash.travellizermono.api.repository.UserRepository;
import com.nyash.travellizermono.security.JwtResponse;
import com.nyash.travellizermono.security.LoginRequest;
import com.nyash.travellizermono.security.MessageResponse;
import com.nyash.travellizermono.security.SignupRequest;
import com.nyash.travellizermono.security.config.jwt.JwtUtils;
import com.nyash.travellizermono.security.service.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping(SIGN_UP)
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        UserEntity user = new UserEntity(
                signupRequest.getUserName(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByRoleName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin":
                        Role adminRole = roleRepository
                                .findByRoleName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;
                    case "manager":
                        Role managerRole = roleRepository
                                .findByRoleName(ERole.MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error, Role MANAGER is not found"));
                        roles.add(managerRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByRoleName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user)

        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
