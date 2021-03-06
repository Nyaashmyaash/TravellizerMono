package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Defines CRUD methods to access User objects in the persistent storage
 *
 * @author Nyash
 *
 */
public interface UserRepository extends JpaRepository <UserEntity, Long> {

    Optional<UserEntity> findByUsername(String userName);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


//    @Query("SELECT u FROM UserEntity u " +
//            "WHERE :isFiltered = FALSE " +
//            "OR (LOWER(u.username) LIKE LOWER(CONCAT('%', :filter, '%'))" +
//            "OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :filter, '%'))" +
//            "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :filter, '%')))" +
//            "ORDER BY u.username, u.lastName, u.firstName")
//    List<UserEntity> findAllByFilter(boolean isFiltered, String filter);
}
