package com.example.BookingMovieTickets.Payload.Request.Seat;

import lombok.Data;

@Data
public class AddSeatRequest {
    private int number;
    private int seatStatusId;
    private String line;
    private int roomId;
    private boolean isActive;
    private int seatTypeId;
}
