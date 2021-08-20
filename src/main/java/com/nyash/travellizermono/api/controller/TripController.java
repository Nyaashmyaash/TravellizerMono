package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.TripDTO;
import com.nyash.travellizermono.api.entity.trip.TripEntity;
import com.nyash.travellizermono.api.factory.TripDTOFactory;
import com.nyash.travellizermono.api.service.TripService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@Transactional
public class TripController {

    TripService tripService;

    TripDTOFactory tripDtoFactory;

    public static final String CREATE_TRIP = "api/trips";
    public static final String FETCH_TRIP = "api/trips";

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
        return ResponseEntity.status(HttpStatus.CREATED).body(tripDtoFactory.createTripDTO(trip));
    }


}
