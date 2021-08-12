package com.nyash.travellizermono.api.service;

import com.nyash.travellizermono.api.entity.user.UserEntity;

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
     * @param userEntity
     */
    void save(UserEntity userEntity);

    /**
     * Returns user with specified identifier boxed into Optional
     * @param userId
     * @return
     */
    Optional<UserEntity> findById(Long userId);

    /**
     * Returns user with specified username
     * @param userName
     * @return
     */
    Optional<UserEntity> findByUserName(String userName);

    void delete(Long userId);

    List<UserEntity> findAll();
}
