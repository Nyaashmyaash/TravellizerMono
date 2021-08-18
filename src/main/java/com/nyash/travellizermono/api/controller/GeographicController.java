package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.exception.BadRequestException;
import com.nyash.travellizermono.api.common.infra.exception.NotFoundException;
import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.AckDTO;
import com.nyash.travellizermono.api.dto.CityDTO;
import com.nyash.travellizermono.api.dto.StationDTO;
import com.nyash.travellizermono.api.entity.geography.CityEntity;
import com.nyash.travellizermono.api.entity.geography.StationEntity;
import com.nyash.travellizermono.api.entity.geography.TransportType;
import com.nyash.travellizermono.api.factory.CityDTOFactory;
import com.nyash.travellizermono.api.factory.StationDTOFactory;
import com.nyash.travellizermono.api.repository.CityRepository;
import com.nyash.travellizermono.api.repository.StationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@Transactional
public class GeographicController {

    CityRepository cityRepository;

    StationRepository stationRepository;

    CityDTOFactory cityDtoFactory;

    StationDTOFactory stationDtoFactory;

    public static final String FETCH_CITIES = "api/cities";
    public static final String CREATE_CITY = "api/cities";
    public static final String UPDATE_CITY = "api/cities/{cityId}";
    public static final String DELETE_CITY = "api/cities/{cityId}";
    public static final String FETCH_STATIONS = "api/cities/{cityId}/stations";
    public static final String CREATE_OR_UPDATE_STATION = "api/cities/{cityId}/stations";


    @GetMapping(FETCH_CITIES)
    public ResponseEntity<List<CityDTO>> fetchCities(
            @RequestParam(defaultValue = "") String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<CityEntity> cities = cityRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(cityDtoFactory.createCityDTOList(cities));
    }

    @PostMapping(CREATE_CITY)
    public ResponseEntity<CityDTO> createCity(
            @RequestParam String name,
            @RequestParam String district,
            @RequestParam String region) {

        CityEntity city = cityRepository.saveAndFlush(
                CityEntity.makeDefault(
                        name,
                        district,
                        region));

        return ResponseEntity.ok(cityDtoFactory.createCityDTO(city));
    }

    @PostMapping(UPDATE_CITY)
    public ResponseEntity<CityDTO> updateCity(
            @PathVariable Long cityId,
            @RequestParam String name,
            @RequestParam String district,
            @RequestParam String region) {

        CityEntity city = cityRepository
                .findById(cityId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("City with ID \"%s\" not found", cityId)));

        city.setName(name);
        city.setDistrict(district);
        city.setRegion(region);

        CityEntity updatedCity = cityRepository.saveAndFlush(city);

        return ResponseEntity.ok(cityDtoFactory.createCityDTO(updatedCity));
    }

    @DeleteMapping(DELETE_CITY)
    public ResponseEntity<AckDTO> deleteCity(
            @PathVariable Long cityId) {
        if (cityRepository.existsById(cityId)) {
            cityRepository.deleteById(cityId);
        }

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }

    @GetMapping(FETCH_STATIONS)
    public ResponseEntity<List<StationDTO>> fetchStations(
            @PathVariable Long cityId,
            @RequestParam(defaultValue = "") String filter) {

        CityEntity city = cityRepository
                .findById(cityId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("City with ID \"%s\" not found", cityId)));
        //TODO: make geographic validation UTIL class
        List<StationEntity> stations = stationRepository.findAllByCityId(cityId);

        return ResponseEntity.ok(stationDtoFactory.createStationDTOList(stations));
    }

    @PutMapping(CREATE_OR_UPDATE_STATION)
    public ResponseEntity<StationDTO> createOrUpdateStation(
            @PathVariable Long cityId,
            @RequestParam String stationName,
            @RequestParam String zipCode,
            @RequestParam String street,
            @RequestParam String houseNumber,
            @RequestParam String apartment,
            @RequestParam String phone,
            @RequestParam Double x,
            @RequestParam Double y,
            @RequestParam TransportType transportType) {

        CityEntity city = cityRepository
                .findById(cityId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("City with ID \"%s\" not found", cityId)));



//        if (!city.getStationEntities()
//                .contains(stationRepository.getByStationName(stationName))) {

            StationEntity station = stationRepository.saveAndFlush(
                    StationEntity.makeDefault(
                            stationName,
                            city,
                            zipCode,
                            street,
                            houseNumber,
                            apartment,
                            phone,
                            x,
                            y,
                            transportType
                    ));

            city.addStation(station);

//        } else {
//
//            StationEntity station = stationRepository.getByStationName(stationName);
//
//            updStation.setStationName(stationName);
//            updStation.setZipCode(zipCode);
//            updStation.setStreet(street);
//            updStation.setHouseNumber(houseNumber);
//            updStation.setApartment(apartment);
//            updStation.setPhone(phone);
//            updStation.setX(x);
//            updStation.setY(y);
//            updStation.setTransportType(transportType);
//
//            stationRepository.saveAndFlush(station);
//        }

        return ResponseEntity.ok(stationDtoFactory.createStationDTO(station));
    }
}
