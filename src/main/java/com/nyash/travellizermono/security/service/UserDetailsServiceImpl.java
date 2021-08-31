package com.nyash.travellizermono.security.service;

import com.nyash.travellizermono.api.entity.user.UserEntity;
import com.nyash.travellizermono.api.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Not found user with username: " + username));
        return UserDetailsImpl.build(user);
    }
}
