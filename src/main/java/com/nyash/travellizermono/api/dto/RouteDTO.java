package com.nyash.travellizermono.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteDTO {

    Long id;

    String start;

    String destination;

    LocalTime startTime;

    LocalTime endTime;

    Double price;
}
