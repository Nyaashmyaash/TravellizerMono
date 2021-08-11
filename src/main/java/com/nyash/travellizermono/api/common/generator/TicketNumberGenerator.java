package com.nyash.travellizermono.api.common.generator;

import com.nyash.travellizer.common.generator.text.RandomDigitStringGenerator;
import com.nyash.travellizer.model.entity.ticket.Ticket;

public class TicketNumberGenerator extends RandomDigitStringGenerator {
    public TicketNumberGenerator() {
        super(Ticket.TICKET_NUMBER_SIZE);
    }
}
