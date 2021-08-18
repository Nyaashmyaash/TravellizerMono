package com.nyash.travellizermono.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyash.travellizermono.api.entity.geography.StationEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityDTO {

    Long id;

    @NonNull
    String name;

    @NonNull
    String district;

    @NonNull
    String region;

    @JsonIgnore
    Set<StationEntity> stations;
}
