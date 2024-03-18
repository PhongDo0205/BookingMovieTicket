package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class CinemaDTO {
    private int id;
    private String address;
    private String description;
    private String code;
    private String nameOfCinema;
    private boolean isActive;
}
