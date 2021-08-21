package com.nyash.travellizermono.api.common.generator;


import com.nyash.travellizermono.api.common.generator.text.RandomDigitStringGenerator;
import com.nyash.travellizermono.api.entity.ticket.TicketEntity;

public class TicketNumberGenerator extends RandomDigitStringGenerator {
    public TicketNumberGenerator() {
        super(TicketEntity.TICKET_NUMBER_SIZE);
    }
}
