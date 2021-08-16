package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.CityDTO;
import com.nyash.travellizermono.api.repository.CityRepository;
import com.nyash.travellizermono.api.repository.StationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@Transactional
public class GeographicController {

    CityRepository cityRepository;

    StationRepository stationRepository;

    public static final String FETCH_CITIES = "api/cities";

    @GetMapping(FETCH_CITIES)
    public ResponseEntity<List<CityDTO>> fetchCities () {
        return null;
    }
}
