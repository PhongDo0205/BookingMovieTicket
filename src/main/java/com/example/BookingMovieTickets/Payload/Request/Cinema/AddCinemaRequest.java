package com.example.BookingMovieTickets.Payload.Request.Cinema;

import lombok.Data;

@Data
public class AddCinemaRequest {
    private String address;
    private String description;
    private String code;
    private String nameOfCinema;
    private boolean isActive;
}
