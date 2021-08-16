package com.nyash.travellizermono.api.factory;

import com.nyash.travellizermono.api.dto.CityDTO;
import com.nyash.travellizermono.api.entity.geography.CityEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDTOFactory {

    public CityDTO createCityDTO(CityEntity cityEntity) {
        return CityDTO.builder()
                .id(cityEntity.getId())
                .name(cityEntity.getName())
                .district(cityEntity.getDistrict())
                .region(cityEntity.getRegion())
                .stations(cityEntity.getStationEntities())
                .build();
    }

    public List<CityDTO> createCityDTOList(List<CityEntity> cityEntities) {
        return cityEntities
                .stream()
                .map(this::createCityDTO)
                .collect(Collectors.toList());
    }
}
