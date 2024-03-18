package com.example.BookingMovieTickets.Payload.Request.Ticket;

import lombok.Data;

@Data
public class AddTicketRequest {
    private String code;
    private int scheduleId;
    private int seatId;
    private boolean isActive;
    private double priceTicket;
}
