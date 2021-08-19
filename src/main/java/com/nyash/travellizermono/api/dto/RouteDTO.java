package com.nyash.travellizermono.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("start_time")
    LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("end_time")
    LocalTime endTime;

    Double price;
}
