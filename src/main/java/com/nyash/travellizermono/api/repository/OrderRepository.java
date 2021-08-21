package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.ticket.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Defines CRUD methods to access Order objects in the persistent storage
 *
 * @author Nyash
 *
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    /**
     * Returns all the orders for given trip
     * @param tripId
     * @return
     */
    List<OrderEntity> findByTripId(@Param("tripId") Long tripId);

    /**
     * Returns all the orders for given user
     * @param userId
     * @return
     */
    List<OrderEntity> findByCreatedBy(String userId);
}
