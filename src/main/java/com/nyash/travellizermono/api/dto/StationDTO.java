package com.nyash.travellizermono.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyash.travellizermono.api.entity.geography.CityEntity;
import com.nyash.travellizermono.api.entity.geography.TransportType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StationDTO {

    @NonNull
    Long id;

    @JsonIgnore
    CityEntity cityEntity;

    String stationName;

    String zipCode;

    String street;

    String houseNumber;

    String apartment;

    @NonNull
    String phone;

    double x;

    double y;

    @NonNull
    TransportType transportType;
}
