package com.nyash.travellizermono.api.controller;

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

/**
 *
 *{@link GeographicController} is REST controller that handles city and station-related requests
 *
 * @author Nyash
 */

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@Transactional
public class GeographicController {


    CityRepository cityRepository;

    StationRepository stationRepository;

    /**
     *City DTO <-> Entity transformation
     */
    CityDTOFactory cityDtoFactory;

    /**
     *Station DTO <-> Entity transformation
     */
    StationDTOFactory stationDtoFactory;

    /**
     * Endpoints
     */
    public static final String FETCH_CITIES = "api/cities";
    public static final String CREATE_CITY = "api/cities";
    public static final String UPDATE_CITY = "api/cities/{cityId}";
    public static final String DELETE_CITY = "api/cities/{cityId}";
    public static final String FETCH_STATIONS = "api/cities/{cityId}/stations";
    public static final String CREATE_STATION = "api/cities/{cityId}/stations";
    public static final String UPDATE_STATION = "api/cities/{cityId}/stations/{stationId}";
    public static final String DELETE_STATION = "api/cities/{cityId}/stations/{stationId}";

    /**
     * Returns all existing cities by filter
     *
     * @param filter
     * @return
     */
    @GetMapping(FETCH_CITIES)
    public ResponseEntity<List<CityDTO>> fetchCities(
            @RequestParam(defaultValue = "") String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<CityEntity> cities = cityRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(cityDtoFactory.createCityDTOList(cities));
    }

    /**
     * Creates new city instance
     *
     * @param name
     * @param district
     * @param region
     * @return
     */
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

    /**
     * Updates city instance
     *
     * @param cityId
     * @param name
     * @param district
     * @param region
     * @return
     */
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

    /**
     * Delete city instance
     *
     * @param cityId
     * @return
     */
    @DeleteMapping(DELETE_CITY)
    public ResponseEntity<AckDTO> deleteCity(
            @PathVariable Long cityId) {
        if (cityRepository.existsById(cityId)) {
            cityRepository.deleteById(cityId);
        }

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }

    /**
     * Returns all stations in this city
     *
     * @param cityId
     * @param filter
     * @return
     */
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

    /**
     * Creates new station instance
     *
     * @param cityId
     * @param stationName
     * @param zipCode
     * @param street
     * @param houseNumber
     * @param apartment
     * @param phone
     * @param x
     * @param y
     * @param transportType
     * @return
     */
    @PostMapping(CREATE_STATION)
    public ResponseEntity<StationDTO> createStation(
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

        return ResponseEntity.ok(stationDtoFactory.createStationDTO(station));
    }

    /**
     * Updates station instance
     *
     * @param cityId
     * @param stationId
     * @param stationName
     * @param zipCode
     * @param street
     * @param houseNumber
     * @param apartment
     * @param phone
     * @param x
     * @param y
     * @param transportType
     * @return
     */
    @PostMapping(UPDATE_STATION)
    public ResponseEntity<StationDTO> updateStation(
            @PathVariable Long cityId,
            @PathVariable Long stationId,
            @RequestParam String stationName,
            @RequestParam String zipCode,
            @RequestParam String street,
            @RequestParam String houseNumber,
            @RequestParam String apartment,
            @RequestParam String phone,
            @RequestParam Double x,
            @RequestParam Double y,
            @RequestParam TransportType transportType) {

        cityRepository
                .findById(cityId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("City with ID \"%s\" not found", cityId)));


        StationEntity station = stationRepository
                .findById(stationId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Station with ID \"%s\" not found", stationId)));

        station.setStationName(stationName);
        station.setZipCode(zipCode);
        station.setStreet(street);
        station.setHouseNumber(houseNumber);
        station.setApartment(apartment);
        station.setPhone(phone);
        station.setX(x);
        station.setY(y);
        station.setTransportType(transportType);

        stationRepository.saveAndFlush(station);

        return ResponseEntity.ok(stationDtoFactory.createStationDTO(station));
    }

    /**
     * Delete station instance
     *
     * @param cityId
     * @param stationId
     * @return
     */
    @DeleteMapping(DELETE_STATION)
    public ResponseEntity<AckDTO> deleteStation(
            @PathVariable Long cityId,
            @PathVariable Long stationId) {

        cityRepository
                .findById(cityId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("City with ID \"%s\" not found", cityId)));

        if (stationRepository.existsById(stationId)) {
            stationRepository.deleteById(stationId);
        }

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }
}
