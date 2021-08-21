package com.nyash.travellizermono.api.service.impl;

import com.nyash.travellizermono.api.common.infra.exception.flow.InvalidParameterException;
import com.nyash.travellizermono.api.entity.trip.RouteEntity;
import com.nyash.travellizermono.api.entity.trip.TripEntity;
import com.nyash.travellizermono.api.repository.RouteRepository;
import com.nyash.travellizermono.api.repository.TripRepository;
import com.nyash.travellizermono.api.service.TripService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TripServiceImpl implements TripService {

    RouteRepository routeRepository;

    TripRepository tripRepository;

    @Override
    public List<RouteEntity> fetchRoutes(final String start, final String destination) {

        RouteEntity probe = new RouteEntity();
        if (!StringUtils.isEmpty(start)) {
            probe.setStart(start);
        }
        if (!StringUtils.isEmpty(destination)) {
            probe.setDestination(destination);
        }

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id", "price");

        Example<RouteEntity> example = Example.of(probe, exampleMatcher);

        return  routeRepository.findAll(example);
    }

    @Override
    public Optional<RouteEntity> fetchRouteById(Long routeId) {
        return routeRepository.findById(routeId);
    }

    @Override
    public void saveRoute(RouteEntity route) {
        routeRepository.save(route);
    }

    @Override
    public void deleteRoute(Long routeId) {
        routeRepository.deleteById(routeId);
    }

    @Override
    public List<TripEntity> fetchTrips(Long routeId) {
        return tripRepository.findByRouteEntityId(routeId);
    }

    @Override
    public Optional<TripEntity> fetchTripById(Long id) {
        return tripRepository.findById(id);
    }

    @Override
    public void saveTrip(TripEntity trip) {
        tripRepository.save(trip);
    }

    @Override
    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }

    @Override
    public TripEntity fetchTrip(Long routeId, LocalDate date) {
        return tripRepository.findByRouteEntityIdAndStartDate(routeId, date);
    }

    @Override
    public TripEntity saveTrip(Long routeId, LocalDate date) {

        RouteEntity route = fetchRouteById(routeId)
                .orElseThrow(() -> new InvalidParameterException("Route with id: " + routeId + " doesn't exist"));

        TripEntity trip = new TripEntity();
        trip.setRouteEntity(route);
        trip.setPrice(route.getPrice());
        trip.setStartDate(date);
        trip.setEndDate(date);
        trip.setStartTime(route.getStartTime());
        trip.setEndTime(route.getEndTime());

        return tripRepository.save(trip);
    }
}
