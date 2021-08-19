package com.nyash.travellizermono.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("station_name")
    String stationName;

    @JsonProperty("zip_code")
    String zipCode;

    String street;

    @JsonProperty("house_number")
    String houseNumber;

    String apartment;

    @NonNull
    String phone;

    double x;

    double y;

    @JsonProperty("transport_type")
    @NonNull
    TransportType transportType;
}
