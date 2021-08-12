package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Defines CRUD methods to access User objects in the persistent storage
 *
 * @author Nyash
 *
 */
public interface UserRepository extends JpaRepository <User, Long> {

    /**
     * Returns user with specified username
     *
     * @param userName
     * @return
     */
    Optional<User> findByUserName(@Param("userName") String userName);
}
