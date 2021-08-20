package com.nyash.travellizermono.api.service;

import com.nyash.travellizermono.api.entity.trip.RouteEntity;
import com.nyash.travellizermono.api.entity.trip.TripEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface TripService {

    /**
     * Returns list of existing routes between these two stations
     * @param destination
     * @param start
     *
     * @return
     */
    List<RouteEntity> fetchRoutes(String start, String destination);

    /**
     * Returns route with specified identifier. If no route is found then empty
     * optional is returned
     *
     * @param id
     * @return
     */
    Optional<RouteEntity> fetchRouteById(Long id);

    /**
     * Saves specified route instance
     *
     * @param route
     */
    void saveRoute(RouteEntity route);

    /**
     * Delete route with specified identifier
     *
     * @param routeId
     */
    void deleteRoute(Long routeId);

    /**
     * Returns list of existing trips for the specified route
     *
     * @return
     */
    List<TripEntity> fetchTrips(Long routeId);

    /**
     * Returns route with specified identifier. If no route is found then empty
     * optional is returned
     *
     * @param id
     * @return
     */
    Optional<TripEntity> fetchTripById(Long id);

    /**
     * Saves specified trip instance
     *
     * @param trip
     */
    void saveTrip(TripEntity trip);

    /**
     * Delete trip with specified identifier
     *
     * @param tripId
     */
    void deleteTrip(Long tripId);

    /**
     * Returns true if trip with such route identifier and trip date already exists
     * @param routeId
     * @param date
     * @return
     */
    TripEntity fetchTrip(Long routeId, LocalDate date);

    /**
     * Saves new trip based on given route identifier and trip date
     * @param routeId
     * @param date
     * @return
     */
    TripEntity saveTrip(Long routeId, LocalDate date);
}
