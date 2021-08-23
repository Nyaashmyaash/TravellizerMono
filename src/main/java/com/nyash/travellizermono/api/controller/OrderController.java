package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.util.SecurityUtil;
import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.OrderDTO;
import com.nyash.travellizermono.api.entity.ticket.OrderEntity;
import com.nyash.travellizermono.api.factory.OrderDTOFactory;
import com.nyash.travellizermono.api.service.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@Transactional
public class OrderController {

    TicketService ticketService;

    OrderDTOFactory orderDtoFactory;

    public static final String FETCH_ORDERS_FOR_USERS = "api/users/{userId}/orders";
    public static final String FETCH_FOR_CURRENT_USER_ORDERS = "api/users/orders";
    public static final String FETCH_ORDERS_FOR_CURRENT_USER = "api/users/";
    public static final String FETCH_ORDERS = "api/orders";
    public static final String CREATE_ORDER = "api/orders";

    @GetMapping(FETCH_ORDERS_FOR_USERS)
    public ResponseEntity<List<OrderDTO>> fetchOrdersForUsers(@PathVariable String userId) {

        List<OrderEntity> orders = ticketService.findOrders(userId);

        return ResponseEntity.ok(orderDtoFactory.createOrderDTOList(orders));
    }

    @GetMapping(FETCH_ORDERS_FOR_CURRENT_USER)
    public ResponseEntity<List<OrderDTO>> fetchOrdersForCurrentUser() {
        //TODO: Assign a current user ID to the order
        return null;
    }

    @GetMapping(FETCH_ORDERS)
    public ResponseEntity<List<OrderDTO>> fetchAllOrders() {

        List<OrderEntity> orders = ticketService.findOrders();

        return ResponseEntity.ok(orderDtoFactory.createOrderDTOList(orders));
    }

    @PostMapping(path = CREATE_ORDER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(
            HttpServletRequest request,
            @RequestParam Long tripId,
            @RequestParam String clientName,
            @RequestParam String clientPhone) {

        OrderEntity order = new OrderEntity();

        order.setTripId(tripId);
        order.setClientName(clientName);
        order.setClientPhone(clientPhone);
        order.setCreatedBy(SecurityUtil.getUserID(request::getHeader));

        ticketService.makeReservation(order);
    }


}
