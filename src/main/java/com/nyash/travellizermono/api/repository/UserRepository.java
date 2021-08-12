package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Defines CRUD methods to access User objects in the persistent storage
 *
 * @author Nyash
 *
 */
public interface UserRepository extends JpaRepository <UserEntity, Long> {

    /**
     * Returns user with specified username
     *
     * @param userName
     * @return
     */
    Optional<UserEntity> findByUserName(@Param("userName") String userName);
}
