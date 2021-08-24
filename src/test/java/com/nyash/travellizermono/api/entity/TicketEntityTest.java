package com.nyash.travellizermono.api.entity;

import com.nyash.travellizermono.api.common.generator.TicketNumberGenerator;
import com.nyash.travellizermono.api.common.generator.text.StringGenerator;
import com.nyash.travellizermono.api.common.infra.exception.flow.InvalidParameterException;
import com.nyash.travellizermono.api.entity.ticket.TicketEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketEntityTest {

    private TicketEntity ticket;

    @BeforeEach
    void setup() {
        ticket = new TicketEntity();
        ticket.setId(1L);
    }

    @Test
    void generateUid_validGenerator_uidAssigned() {
        StringGenerator generator = new TicketNumberGenerator();
        ticket.generateUid(generator);

        assertNotNull(ticket.getUid());
        assertEquals(TicketEntity.TICKET_NUMBER_SIZE, ticket.getUid().length());
    }

    @Test
    void generateUid_nullGenerator_exceptionThrown() {
        assertThrows(InvalidParameterException.class, () -> ticket.generateUid(null));
    }
}
