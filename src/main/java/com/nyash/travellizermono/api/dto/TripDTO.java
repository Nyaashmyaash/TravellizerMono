package com.nyash.travellizermono.api.dto;

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
    @NonNull
    LocalDate startDate;

    @NonNull
    LocalDate endDate;

    @NonNull
    Integer maxSeats;

    @NonNull
    Integer availableSeats;

    @NonNull
    Double price;
}
