package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.exception.NotFoundException;
import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.AckDTO;
import com.nyash.travellizermono.api.dto.CityDTO;
import com.nyash.travellizermono.api.dto.RouteDTO;
import com.nyash.travellizermono.api.entity.geography.CityEntity;
import com.nyash.travellizermono.api.entity.trip.RouteEntity;
import com.nyash.travellizermono.api.factory.RouteDTOFactory;
import com.nyash.travellizermono.api.factory.TripDTOFactory;
import com.nyash.travellizermono.api.repository.RouteRepository;
import com.nyash.travellizermono.api.repository.TripRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@Transactional
public class TripController {

    TripRepository tripRepository;

    TripDTOFactory tripDtoFactory;

    RouteRepository routeRepository;

    RouteDTOFactory routeDtoFactory;

    public static final String FETCH_ROUTE = "api/routes";
    public static final String CREATE_ROUTE = "api/routes";
    public static final String UPDATE_ROUTE = "api/routes/{routeId}";
    public static final String DELETE_ROUTE = "api/routes/{routeId}";

    @GetMapping(FETCH_ROUTE)
    public ResponseEntity<List<RouteDTO>> fetchRoutes(
            @RequestParam(defaultValue = "") String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<RouteEntity> routes = routeRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(routeDtoFactory.createRouteDTOList(routes));
    }

    @PostMapping(CREATE_ROUTE)
    public ResponseEntity<RouteDTO> createRoute(
            @RequestParam String start,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(pattern = "H[H]:mm:ss") LocalTime startTime,
            @RequestParam @DateTimeFormat(pattern = "H[H]:mm:ss") LocalTime endTime,
            @RequestParam Double price) {

        RouteEntity route = routeRepository.saveAndFlush(
                RouteEntity.makeDefault(
                        start,
                        destination,
                        startTime,
                        endTime,
                        price
                )
        );

        return ResponseEntity.ok(routeDtoFactory.createRouteDTO(route));
    }

    @PostMapping(UPDATE_ROUTE)
    public ResponseEntity<RouteDTO> updateRoute(
            @PathVariable Long routeId,
            @RequestParam String start,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(pattern = "H[H]:mm:ss") LocalTime startTime,
            @RequestParam @DateTimeFormat(pattern = "H[H]:mm:ss") LocalTime endTime,
            @RequestParam Double price) {

        RouteEntity route = routeRepository
                .findById(routeId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Route with ID \"%s\" not found", routeId)));

        route.setStart(start);
        route.setDestination(destination);
        route.setStartTime(startTime);
        route.setEndTime(endTime);
        route.setPrice(price);

        routeRepository.saveAndFlush(route);

        return ResponseEntity.ok(routeDtoFactory.createRouteDTO(route));
    }

    @DeleteMapping(DELETE_ROUTE)
    public ResponseEntity<AckDTO> deleteRoute(
            @PathVariable Long routeId) {

        if (routeRepository.existsById(routeId)) {
            routeRepository.deleteById(routeId);
        }
        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }
}
