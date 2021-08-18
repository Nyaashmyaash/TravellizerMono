package com.nyash.travellizermono.api.factory;

import com.nyash.travellizermono.api.dto.StationDTO;
import com.nyash.travellizermono.api.entity.geography.StationEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StationDTOFactory {

    public StationDTO createStationDTO(StationEntity stationEntity) {
        return StationDTO.builder()
                .id(stationEntity.getId())
                .cityEntity(stationEntity.getCityEntity())
                .stationName(stationEntity.getStationName())
                .zipCode(stationEntity.getZipCode())
                .street(stationEntity.getStreet())
                .houseNumber(stationEntity.getHouseNumber())
                .apartment(stationEntity.getApartment())
                .phone(stationEntity.getPhone())
                .x(stationEntity.getX())
                .y(stationEntity.getY())
                .transportType(stationEntity.getTransportType())
                .build();
    }

    public List<StationDTO> createStationDTOList(List<StationEntity> stationEntities) {
        return stationEntities
                .stream()
                .map(this::createStationDTO)
                .collect(Collectors.toList());
    }
}
