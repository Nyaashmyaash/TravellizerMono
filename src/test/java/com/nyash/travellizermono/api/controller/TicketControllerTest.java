package com.nyash.travellizermono.api.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nyash.travellizermono.TravellizerMonoApplication;
import com.nyash.travellizermono.api.dto.TicketDTO;
import com.nyash.travellizermono.api.entity.ticket.TicketEntity;
import com.nyash.travellizermono.api.service.TicketService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@SpringJUnitWebConfig(TravellizerMonoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
class TicketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TicketService ticketService;

    @Autowired
    JacksonTester<TicketDTO> ticketTester;

//    @Test
//    void findTickets_noTripsCreated_returnsEmptyList() throws Exception {
//        ResultActions result = mockMvc.perform(get("/api/tickets/1000"));
//
//        result.andExpect(status().isOk()).andExpect(
//                content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()",
//                        equalTo(0))).andDo(document("{class-name}/{method-name}"));
//    }

    @Test
    void findTickets_tripExists_returnsSingleTrip() throws Exception {
        Long tripId = 1L;
        String client = "User1";
        TicketEntity ticket = ticketService.buyTicket(tripId, client);
        assumeFalse(ticket == null);

        ResultActions result = mockMvc.perform(get("api/tickets/{tripId}", tripId));

        result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].tripId", equalTo(tripId))).andExpect(jsonPath("$.[0].name", equalTo(client)))
                .andExpect(jsonPath("$.[0].uid", notNullValue()))
                .andDo(document("{class-name}/{method-name}",
                        responseFields(fieldWithPath("[].tripId").description("Identifier of the trip"),
                                fieldWithPath("[].name").description("Name of the client who purchases the ticket"),
                                fieldWithPath("[].uid").description("Generated unique ticket name"))));
    }
}