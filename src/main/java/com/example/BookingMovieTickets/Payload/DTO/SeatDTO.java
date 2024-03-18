package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class SeatDTO {
    private int id;
    private int number;
    private int seatStatusId;
    private String line;
    private int roomId;
    private boolean isActive;
    private int seatTypeId;
}
