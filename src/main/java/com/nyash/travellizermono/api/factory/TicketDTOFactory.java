package com.nyash.travellizermono.api.factory;

import com.nyash.travellizermono.api.dto.TicketDTO;
import com.nyash.travellizermono.api.entity.ticket.TicketEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketDTOFactory {

    public TicketDTO createTicketDTO(TicketEntity ticketEntity) {
        return TicketDTO.builder()
                .tripId(ticketEntity.getTripId())
                .name(ticketEntity.getName())
                .uid(ticketEntity.getUid())
                .build();
    }

    public List<TicketDTO> createTicketDTOList(List<TicketEntity> ticketEntities) {
        return ticketEntities
                .stream()
                .map(this::createTicketDTO)
                .collect(Collectors.toList());
    }
}
