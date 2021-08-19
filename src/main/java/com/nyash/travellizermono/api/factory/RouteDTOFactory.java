package com.nyash.travellizermono.api.factory;

import com.nyash.travellizermono.api.dto.RouteDTO;
import com.nyash.travellizermono.api.dto.TripDTO;
import com.nyash.travellizermono.api.entity.trip.RouteEntity;
import com.nyash.travellizermono.api.entity.trip.TripEntity;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteDTOFactory {

    public RouteDTO createRouteDTO(RouteEntity routeEntity) {
        return RouteDTO.builder()
                .id(routeEntity.getId())
                .start(routeEntity.getStart())
                .destination(routeEntity.getDestination())
                .startTime(routeEntity.getStartTime())
                .endTime(routeEntity.getEndTime())
                .price(routeEntity.getPrice())
                .build();
    }

    public List<RouteDTO> createRouteDTOList(List<RouteEntity> routeEntities) {
        return routeEntities
                .stream()
                .map(this::createRouteDTO)
                .collect(Collectors.toList());
    }
}
