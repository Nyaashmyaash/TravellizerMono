package com.nyash.travellizermono.api.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyash.travellizermono.api.entity.user.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    Long userId;

    String username;

    @JsonIgnore
    String password;

//    String firstName;

//    String lastName;

    String email;

    Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
//                user.getFirstName(),
//                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
