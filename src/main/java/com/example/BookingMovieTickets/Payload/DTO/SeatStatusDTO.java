package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class SeatStatusDTO {
    private int id;
    private String code;
    private String nameStatus;
}
