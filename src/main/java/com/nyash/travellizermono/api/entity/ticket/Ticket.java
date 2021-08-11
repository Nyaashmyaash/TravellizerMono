package com.nyash.travellizermono.api.entity.ticket;

import com.nyash.travellizer.common.generator.text.StringGenerator;
import com.nyash.travellizer.common.infra.util.Checks;
import com.nyash.travellizer.common.model.entity.base.AbstractEntity;
import lombok.Setter;

/**
 * Trip ticket
 *
 * @author Nyash
 */
@Setter
public class Ticket extends AbstractEntity {

    /**
     * Size of the generated ticket number
     */
    public static final int TICKET_NUMBER_SIZE = 32;

    /**
     * Link to the underlying trip
     */
    private String tripId;

    /**
     * Client name
     */
    private String name;

    /**
     * Auto-generated ticket identifier(usually random)
     */
    private String uid;

    public String getTripId() {
        return tripId;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    /**
     * Generates system-unique ticket number
     *
     * @param generator
     */
    public void generateUid(final StringGenerator generator) {
        Checks.checkParameter(generator != null, "StringGenerator should be initialized");
        uid = generator.generate();
    }
}
