package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.exception.NotFoundException;
import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.AckDTO;
import com.nyash.travellizermono.api.dto.TripDTO;
import com.nyash.travellizermono.api.entity.trip.TripEntity;
import com.nyash.travellizermono.api.factory.TripDTOFactory;
import com.nyash.travellizermono.api.repository.RouteRepository;
import com.nyash.travellizermono.api.service.TripService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 *
 *{@link TripController} is REST controller that handles trip requests
 *
 * @author Nyash
 */

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@Transactional
public class TripController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    //TODO: available seats feature
    //TODO: add time parameter

    TripService tripService;

    /**
     *Trip DTO <-> Entity transformation
     */
    TripDTOFactory tripDtoFactory;

    RouteRepository routeRepository;

    /**
     * Endpoints
     */
    public static final String FETCH_TRIPS = "api/trips";
    public static final String CREATE_TRIP = "api/trips";
    public static final String DELETE_TRIP = "api/trips/{tripId}";

    /**
     * Creates new trip
     *
     * @param routeId
     * @param date
     * @return
     */
    @PostMapping(CREATE_TRIP)
    public ResponseEntity<TripDTO> createTrip(
            @RequestParam Long routeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        TripEntity trip = tripService.fetchTrip(routeId, date);

        if (trip != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(tripDtoFactory.createTripDTO(trip));
        }

        trip = tripService.saveTrip(routeId, date);
        LOG.info("New trip for route: " + routeId + " was added");
        return ResponseEntity.status(HttpStatus.CREATED).body(tripDtoFactory.createTripDTO(trip));
    }

    /**
     * Delete trip
     *
     * @param tripId
     * @return
     */
    @DeleteMapping(DELETE_TRIP)
    public ResponseEntity<AckDTO> deleteTrip(
            @PathVariable Long tripId) {

        if (tripService.fetchTripById(tripId).isPresent()) {
            tripService.deleteTrip(tripId);
        }
        LOG.warn("Trip with ID" + tripId + "was deleted");

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }

    /**
     * Returns all existing trips by route ID
     *
     * @param routeId
     * @return
     */
    @GetMapping(FETCH_TRIPS)
    public ResponseEntity<List<TripDTO>> fetchTrips(
            @RequestParam Long routeId) {

        routeRepository
                .findById(routeId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Route with ID \"%s\" not found", routeId)));

        List<TripEntity> trips = tripService.fetchTrips(routeId);

        if (trips.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(tripDtoFactory.createTripDTOList(trips));
    }
}
