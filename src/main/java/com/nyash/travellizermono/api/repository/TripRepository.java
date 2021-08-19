package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.trip.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Defines CRUD methods to access Trip objects in the persistent storage
 * @author Nyash
 */
public interface TripRepository extends JpaRepository<TripEntity, Long> {
    /**
     * Returns list of the trips linked to the specified route
     * @param routeId
     * @return
     */
//    List<TripEntity> findByRouteId(@Param("routeId") Long routeId);

//    TripEntity findByRouteIdAndStartDate(Long routeId, LocalDate date);
}
