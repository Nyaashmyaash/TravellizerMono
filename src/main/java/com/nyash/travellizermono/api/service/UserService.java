package com.nyash.travellizermono.api.service;

import com.nyash.travellizermono.api.entity.user.User;

import java.util.List;
import java.util.Optional;

/**
 * Provides API for the user management
 * @author Nyash
 *
 */
public interface UserService {

    /**
     * Saves(creates or modifies) specified user instance
     * @param user
     */
    void save(User user);

    /**
     * Returns user with specified identifier boxed into Optional
     * @param userId
     * @return
     */
    Optional<User> findById(Long userId);

    /**
     * Returns user with specified username
     * @param userName
     * @return
     */
    Optional<User> findByUserName(String userName);

    void delete(Long userId);

    List<User> findAll();
}
