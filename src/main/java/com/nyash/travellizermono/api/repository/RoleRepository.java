package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.user.ERole;
import com.nyash.travellizermono.api.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(ERole name);
}
