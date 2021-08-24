package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.TicketDTO;
import com.nyash.travellizermono.api.entity.ticket.TicketEntity;
import com.nyash.travellizermono.api.factory.TicketDTOFactory;
import com.nyash.travellizermono.api.service.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@Transactional
public class TicketController {

    TicketService ticketService;

    TicketDTOFactory ticketDTOFactory;

    public static final String FETCH_TICKETS_BY_TRIP_ID = "api/tickets/{tripId}";

    @GetMapping(FETCH_TICKETS_BY_TRIP_ID)
    public ResponseEntity<List<TicketDTO>> fetchTicketsByTripId (
            @PathVariable Long tripId) {

        List<TicketEntity> tickets = ticketService.findTickets(tripId);

        return ResponseEntity.ok(ticketDTOFactory.createTicketDTOList(tickets));
    }
}
