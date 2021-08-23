package com.nyash.travellizermono.api.entity.ticket;

import com.nyash.travellizermono.api.common.infra.exception.flow.ReservationException;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Order of the ticket by site user
 *
 * @author Nyash
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    public static final String FIELD_TRIP_ID = "tripId";

    /**
     * Current order id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * Current order state
     */
    @Builder.Default
    @Enumerated
    @Column(name = "ORDER_STATE")
    OrderState state = OrderState.CREATED;

    /**
     * Date/time when user should pay for the order(ticket)
     */
    @Builder.Default
    @NonNull
    @Column(name = "DUE_DATE")
    LocalDateTime dueDate = LocalDateTime.now().plusDays(1);

    /**
     * Trip unique identifier
     */
    @NonNull
    @Column(name = "TRIP_ID")
    Long tripId;

    /**
     * Link to the payed ticket(if order is completed)
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "TICKET_ID")
    TicketEntity ticketEntity;

    /**
     * Client name/surname
     */
    @NonNull
    @Column(name = "CLIENT_NAME", length = 32)
    String clientName;

    /**
     * Client contact phone for communication
     */
    @NonNull
    @Column(name = "CLIENT_PHONE", length = 24)
    String clientPhone;

    /**
     * If order was cancelled then it's reason of client cancellation
     */
    @Column(name = "CANCELLATION_REASON", length = 128)
    String cancellationReason;

    /**
     * Person who created specific Entity
     */
    String createdBy;

//    public Order() {
//        state = OrderState.CREATED;
//        //TODO use application settings and check the trip start time
//        dueDate = LocalDateTime.now().plusDays(1);
//    }

    /**
     * Cancels current order
     */
    public void cancel(String reason) {
        if (dueDate.isBefore(LocalDateTime.now())) {
            System.out.println("This order misses due date and should be automatically cancelled, id: " + id);
        }
        this.state = OrderState.CANCELLED;
        this.cancellationReason = reason;
    }

    /**
     * Makes necessary checks and completes the order
     */
    public void complete() {
        if (dueDate.isBefore(LocalDateTime.now())) {
            throw new ReservationException("This order misses due date, id: " + id);
        }
        this.state = OrderState.COMPLETED;
    }

    @Transient
    public boolean isCompleted() {
        return state == OrderState.COMPLETED;
    }

    @Transient
    public boolean isCancelled() {
        return state == OrderState.CANCELLED;
    }
}
