package com.nyash.travellizermono.api.service.impl;

import com.nyash.travellizermono.api.common.generator.TicketNumberGenerator;
import com.nyash.travellizermono.api.common.generator.text.StringGenerator;
import com.nyash.travellizermono.api.common.infra.util.Checks;
import com.nyash.travellizermono.api.entity.ticket.OrderEntity;
import com.nyash.travellizermono.api.entity.ticket.TicketEntity;
import com.nyash.travellizermono.api.repository.OrderRepository;
import com.nyash.travellizermono.api.repository.TicketRepository;
import com.nyash.travellizermono.api.service.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;

    OrderRepository orderRepository;

    /**
     * Default generator for ticket numbers
     */
    StringGenerator ticketNumberGenerator = new TicketNumberGenerator();


    @Override
    public List<TicketEntity> findTickets(Long tripId) {
        return ticketRepository.findByTripId(tripId);
    }

    @Override
    public List<OrderEntity> findReservations(Long tripId) {
        return orderRepository.findByTripId(tripId);
    }

    @Override
    public void makeReservation(OrderEntity order) {
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void cancelReservation(Long orderId, String reason) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);

        order.ifPresentOrElse(res -> {
            res.cancel(reason);
            orderRepository.saveAndFlush(res);
        }, () -> log.error("Invalid order identifier: {}", orderId));
    }

    @Override
    public void completeReservation(Long orderId) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);

        order.ifPresentOrElse(res -> {
            res.complete();
            orderRepository.saveAndFlush(res);
        }, () -> log.error("Invalid order identifier: {}", orderId));
    }

    @Override
    public TicketEntity buyTicket(final Long tripId, final String clientName) {

        Checks.checkParameter(tripId != null, "Trip identifier should be not null");

        TicketEntity ticket = new TicketEntity();
        ticket.setTripId(tripId);
        ticket.generateUid(ticketNumberGenerator);
        ticket.setName(clientName);
        ticketRepository.saveAndFlush(ticket);

        return ticket;
    }

    @Override
    public List<OrderEntity> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderEntity> findOrders(String userId) {
        return orderRepository.findByCreatedBy(userId);
    }
}
