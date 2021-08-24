package com.nyash.travellizermono.api.entity;

import com.nyash.travellizermono.api.entity.ticket.OrderEntity;
import com.nyash.travellizermono.api.entity.ticket.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderEntityTest {

    private OrderEntity order;

    @BeforeEach
    void setup() {
        order = new OrderEntity();
        order.setId(1L);
        order.setDueDate(LocalDateTime.now().plusDays(2));
    }

    @Test
    void complete_validOrder_orderCompleted() {
        order.complete();
        assertEquals(order.getState(), OrderState.COMPLETED);
        assertTrue(order.isCompleted());
    }

    @Test
    void complete_expiredOrder_exceptionThrown() {
        order.setDueDate(LocalDateTime.now().minusDays(1));
        assertThrows(Exception.class, () -> order.complete());
    }

    @Test
    void cancel_validOrder_orderCancelled() {
        order.cancel("test");
        assertEquals(order.getState(), OrderState.CANCELLED);
        assertTrue(order.isCancelled());
        assertEquals(order.getCancellationReason(), "test");
    }
}
