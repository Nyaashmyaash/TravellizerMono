package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Defines CRUD methods to access Ticket objects in the persistent storage
 *
 * @author Nyash
 *
 */
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    /**
     * Returns all the tickets for given trip
     * @param tripId
     * @return
     */
    List<TicketEntity> findByTripId(@Param("tripId") Long tripId);
}
