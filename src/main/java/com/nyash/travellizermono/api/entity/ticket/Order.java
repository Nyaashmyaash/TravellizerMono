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
@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {

    /**
     * Current order id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * Current order state
     */
    @Enumerated
    @Column(name = "order_state")
    OrderState state;

    /**
     * Date/time when user should pay for the order(ticket)
     */
    @NonNull
    @Column(name = "due_date")
    LocalDateTime dueDate;

//    /**
//     * Link to the ticket's trip
//     */
//    Trip trip;

    /**
     * Link to the payed ticket(if order is completed)
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id")
    Ticket ticket;

    /**
     * Client name/surname
     */
    @NonNull
    @Column(name = "client_name", length = 32)
    String clientName;

    /**
     * Client contact phone for communication
     */
    @NonNull
    @Column(name = "client_phone", length = 24)
    String clientPhone;

    /**
     * If order was cancelled then it's reason of client cancellation
     */
    @Column(name = "cancellation_reason", length = 128)
    String cancellationReason;

    public Order() {
        state = OrderState.CREATED;
        //TODO use application settings and check the trip start time
        dueDate = LocalDateTime.now().plusDays(1);
    }

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

    public boolean isCompleted() {
        return state == OrderState.COMPLETED;
    }

    public boolean isCancelled() {
        return state == OrderState.CANCELLED;
    }
}
