package com.nyash.travellizermono.api.factory;

import com.nyash.travellizermono.api.dto.TripDTO;
import com.nyash.travellizermono.api.entity.trip.TripEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripDTOFactory {

    public TripDTO createTripDTO(TripEntity tripEntity) {
        return TripDTO.builder()
                .id(tripEntity.getId())
                .routeId(tripEntity.getRouteEntity().getId())
                .startDate(tripEntity.getStartDate())
                .endDate(tripEntity.getEndDate())
                .maxSeats(tripEntity.getMaxSeats())
                .availableSeats(tripEntity.getAvailableSeats())
                .price(tripEntity.getPrice())
                .build();
    }

    public List<TripDTO> createTripDTOList(List<TripEntity> tripEntities) {
        return tripEntities
                .stream()
                .map(this::createTripDTO)
                .collect(Collectors.toList());
    }
}
