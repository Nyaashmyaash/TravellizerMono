package com.nyash.travellizermono.api.service;

import com.nyash.travellizermono.api.entity.ticket.OrderEntity;
import com.nyash.travellizermono.api.entity.ticket.TicketEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    /**
     * Returns all the tickets for the specified trip
     * @param tripId
     * @return
     */
    List<TicketEntity> findTickets(String tripId);

    /**
     * Returns all the reservations for the specified trip
     * @param tripId
     * @return
     */
    List<OrderEntity> findReservations(Long tripId);

    /**
     * Puts an order
     * @param order
     */
    void makeReservation(OrderEntity order);

    /**
     * Cancels ticket reservation with specified identifier
     * @param orderId
     * @param reason
     */
    void cancelReservation(Long orderId, String reason);

    /**
     * Completes reservation and purchases a ticket
     * @param orderId
     */
    void completeReservation(Long orderId);

    /**
     * Purchases a ticket for the specified trip
     * @param tripId
     * @param clientName
     * @return
     */
    TicketEntity buyTicket(String tripId, String clientName);

    /**
     * Returns all the orders
     * @return
     */
    List<OrderEntity> findOrders();

    /**
     * Returns orders of specific user
     * @param userId
     * @return
     */
    List<OrderEntity> findOrders(String userId);
}
