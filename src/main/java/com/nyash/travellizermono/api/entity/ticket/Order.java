package com.nyash.travellizermono.api.entity.ticket;

import com.nyash.travellizer.common.infra.exception.flow.ReservationException;
import com.nyash.travellizer.common.model.entity.base.AbstractEntity;
import com.nyash.travellizer.model.entity.trip.Trip;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Order of the ticket by site user
 *
 * @author Nyash
 */
@Setter
public class Order extends AbstractEntity {

    /**
     * Current order id
     */
    private int id;
    /**
     * Current order state
     */
    private com.nyash.travellizer.model.entity.ticket.OrderState state;

    /**
     * Date/time when user should pay for the order(ticket)
     */
    private LocalDateTime dueDate;

    /**
     * Link to the ticket's trip
     */
    private Trip trip;

    /**
     * Link to the payed ticket(if order is completed)
     */
    private Ticket ticket;

    /**
     * Client name/surname
     */
    private String clientName;

    /**
     * Client contact phone for communication
     */
    private String clientPhone;

    /**
     * If order was cancelled then it's reason of client cancellation
     */
    private String cancellationReason;

    public Order() {
        state = com.nyash.travellizer.model.entity.ticket.OrderState.CREATED;
    }

    public int getId() {
        return id;
    }

    public com.nyash.travellizer.model.entity.ticket.OrderState getState() {
        return state;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Trip getTrip() {
        return trip;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    /**
     * Cancels current order
     */
    public void cancel(String reason) {
        if (dueDate.isBefore(LocalDateTime.now())) {
            System.out.println("This order misses due date and should be automatically cancelled, id: " + id);
        }
        this.state = com.nyash.travellizer.model.entity.ticket.OrderState.CANCELLED;
        this.cancellationReason = reason;
    }

    /**
     * Makes necessary checks and completes the order
     */
    public void complete() {
        if (dueDate.isBefore(LocalDateTime.now())) {
            throw new ReservationException("This order misses due date, id: " + id);
        }
        this.state = com.nyash.travellizer.model.entity.ticket.OrderState.COMPLETED;
    }

    public boolean isCompleted() {
        return state == com.nyash.travellizer.model.entity.ticket.OrderState.COMPLETED;
    }

    public boolean isCancelled() {
        return state == com.nyash.travellizer.model.entity.ticket.OrderState.CANCELLED;
    }
}
