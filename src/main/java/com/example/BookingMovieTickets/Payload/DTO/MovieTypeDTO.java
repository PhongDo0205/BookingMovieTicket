package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class MovieTypeDTO {
    private int id;
    private String movieTypeName;
    private boolean isActive;
}
