package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.trip.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Defines CRUD methods to access Route objects in the persistent storage
 * @author Nyash
 */
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {

    @Query("SELECT r FROM RouteEntity r " +
            "WHERE :isFiltered = FALSE " +
            "OR (LOWER(r.start) LIKE LOWER(CONCAT('%', :filter, '%'))" +
            "OR LOWER(r.destination) LIKE LOWER(CONCAT('%', :filter, '%'))) " +
            "ORDER BY r.start, r.destination")
    List<RouteEntity> findAllByFilter(boolean isFiltered, String filter);
}
