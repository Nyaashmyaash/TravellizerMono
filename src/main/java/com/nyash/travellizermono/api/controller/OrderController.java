package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.service.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@Transactional
public class OrderController {

    TicketService ticketService;

    public static final String FETCH_ORDERS_FOR_USERS = "api/users/{userId}/orders";


}
