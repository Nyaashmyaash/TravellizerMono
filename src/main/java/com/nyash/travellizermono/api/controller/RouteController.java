package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.exception.NotFoundException;
import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.AckDTO;
import com.nyash.travellizermono.api.dto.RouteDTO;
import com.nyash.travellizermono.api.entity.trip.RouteEntity;
import com.nyash.travellizermono.api.factory.RouteDTOFactory;
import com.nyash.travellizermono.api.repository.RouteRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

/**
 *
 *{@link RouteController} is REST controller that handles routes requests
 *
 * @author Nyash
 */

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@Transactional
public class RouteController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    RouteRepository routeRepository;

    /**
     * Route DTO <-> Entity transformation
     */
    RouteDTOFactory routeDtoFactory;

    /**
     * Endpoints
     */
    public static final String FETCH_ROUTE = "api/routes";
    public static final String CREATE_ROUTE = "api/routes";
    public static final String UPDATE_ROUTE = "api/routes/{routeId}";
    public static final String DELETE_ROUTE = "api/routes/{routeId}";

    /**
     *  Returns all existing routes by filter
     *
     * @param filter
     * @return
     */
    @GetMapping(FETCH_ROUTE)
    public ResponseEntity<List<RouteDTO>> fetchRoutes(
            @RequestParam(defaultValue = "") String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<RouteEntity> routes = routeRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(routeDtoFactory.createRouteDTOList(routes));
    }

    /**
     * Creates new route
     *
     * @param start
     * @param destination
     * @param startTime
     * @param endTime
     * @param price
     * @return
     */
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

    /**
     * Updates route
     *
     * @param routeId
     * @param start
     * @param destination
     * @param startTime
     * @param endTime
     * @param price
     * @return
     */
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

    /**
     * Delete route
     *
     * @param routeId
     * @return
     */
    @DeleteMapping(DELETE_ROUTE)
    public ResponseEntity<AckDTO> deleteRoute(
            @PathVariable Long routeId) {

        if (routeRepository.existsById(routeId)) {
            routeRepository.deleteById(routeId);
        }
        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }
    }
