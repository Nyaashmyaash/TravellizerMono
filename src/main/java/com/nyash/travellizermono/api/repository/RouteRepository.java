package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.trip.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Defines CRUD methods to access Route objects in the persistent storage
 * @author Nyash
 */
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
}
