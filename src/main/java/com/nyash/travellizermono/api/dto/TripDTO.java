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

    Long id;

    Long routeId;

    LocalDate startDate;

    LocalDate endDate;

    Integer maxSeats;

    Integer availableSeats;

    Double price;
}
