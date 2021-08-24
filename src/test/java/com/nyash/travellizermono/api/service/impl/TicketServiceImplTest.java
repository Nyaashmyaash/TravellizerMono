package com.nyash.travellizermono.api.service.impl;

import com.nyash.travellizermono.api.entity.ticket.OrderEntity;
import com.nyash.travellizermono.api.repository.OrderRepository;
import com.nyash.travellizermono.api.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {

    @Spy
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Test
    public void cancelReservation_validOrder_orderCancelled() {
        OrderEntity order = new OrderEntity();
        order.setId(1L);
        order.setDueDate(LocalDateTime.now().plusDays(2));

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        ticketService.cancelReservation(order.getId(), "test");

        assertTrue(order.isCancelled());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void cancelReservation_invalidId_orderNotCancelled() {
        when(orderRepository.findById(100L)).thenReturn(Optional.empty());
        ticketService.cancelReservation(100L, "test");

        verify(orderRepository, never()).save(ArgumentMatchers.any(OrderEntity.class));
    }

}
