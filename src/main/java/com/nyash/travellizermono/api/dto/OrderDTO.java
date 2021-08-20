package com.nyash.travellizermono.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {

    String state;

    @JsonProperty("due_date")
    LocalDateTime dueDate;

    Long tripId;

    @JsonProperty("ticket_id")
    String ticketId;

    @JsonProperty("client_name")
    String clientName;

    @JsonProperty("client_phone")
    String clientPhone;

    @JsonProperty("cancellation_reason")
    String cancellationReason;

    @JsonProperty("created_at")
    LocalDateTime createdAt;
}
