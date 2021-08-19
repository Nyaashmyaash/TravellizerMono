package com.nyash.travellizermono.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripDTO {

    @NonNull
    Long id;

    @NonNull
    Long routeId;

    @JsonProperty("start_date")
    @NonNull
    LocalDate startDate;

    @JsonProperty("end_date")
    @NonNull
    LocalDate endDate;

    @JsonProperty("max_seats")
    @NonNull
    Integer maxSeats;

    @JsonProperty("available_seats")
    @NonNull
    Integer availableSeats;

    @NonNull
    Double price;
}
