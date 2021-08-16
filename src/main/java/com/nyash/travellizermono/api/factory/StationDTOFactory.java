package com.nyash.travellizermono.api.factory;

import com.nyash.travellizermono.api.dto.StationDTO;
import com.nyash.travellizermono.api.entity.geography.StationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class StationDTOFactory {

    public StationDTO createStationDTO(StationEntity stationEntity) {
        return StationDTO.builder()
                .id(stationEntity.getId())
                .cityEntity(stationEntity.getCityEntity())
                .stationName(stationEntity.getStationName())
                .zipCode(stationEntity.getAddressEntity().getZipCode())
                .street(stationEntity.getAddressEntity().getStreet())
                .houseNumber(stationEntity.getAddressEntity().getHouseNumber())
                .apartment(stationEntity.getAddressEntity().getApartment())
                .phone(stationEntity.getPhone())
                .x(stationEntity.getCoordinateEntity().getX())
                .y(stationEntity.getCoordinateEntity().getY())
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
