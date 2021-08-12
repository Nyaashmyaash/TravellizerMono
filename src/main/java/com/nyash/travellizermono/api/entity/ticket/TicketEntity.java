package com.nyash.travellizermono.api.entity.ticket;


import com.nyash.travellizermono.api.common.generator.text.StringGenerator;
import com.nyash.travellizermono.api.common.infra.util.Checks;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Trip ticket
 *
 * @author Nyash
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "TICKETS")
public class TicketEntity {

    /**
     * Size of the generated ticket number
     */
    public static final int TICKET_NUMBER_SIZE = 32;

    /**
     * Current ticket id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Link to the underlying trip
     */
    @NonNull
    @Column(name = "TRIP_ID")
    private String tripId;

    /**
     * Client name
     */
    @NonNull
    @Column(name = "CLIENT_NAME", length = 32)
    private String name;

    /**
     * Auto-generated ticket identifier(usually random)
     */
    @NonNull
    @Column(name = "UID", length = 32)
    private String uid;

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
