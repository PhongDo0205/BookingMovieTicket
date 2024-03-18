package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class TicketDTO {
    private int id;
    private String code;
    private int scheduleId;
    private int seatId;
    private boolean isActive;
    private double priceTicket;
}
