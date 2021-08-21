package com.nyash.travellizermono.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("start_date")
    @NonNull
    LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("end_date")
    @NonNull
    LocalDate endDate;

    @JsonProperty("max_seats")
    Integer maxSeats;

    @JsonProperty("available_seats")
    Integer availableSeats;

    @NonNull
    Double price;
}
