package com.nyash.travellizermono.api.factory;

import com.nyash.travellizermono.api.dto.CityDTO;
import com.nyash.travellizermono.api.dto.OrderDTO;
import com.nyash.travellizermono.api.entity.geography.CityEntity;
import com.nyash.travellizermono.api.entity.ticket.OrderEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDTOFactory {

    public OrderDTO createOrderDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .state(orderEntity.getState().toString())
                .dueDate(orderEntity.getDueDate())
                .tripId(orderEntity.getTripId())
                .ticketId(orderEntity.getTicketEntity().getId().toString())
                .clientName(orderEntity.getClientName())
                .clientPhone(orderEntity.getClientPhone())
                .cancellationReason(orderEntity.getCancellationReason())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public List<OrderDTO> createOrderDTOList(List<OrderEntity> orderEntities) {
        return orderEntities
                .stream()
                .map(this::createOrderDTO)
                .collect(Collectors.toList());
    }
}
