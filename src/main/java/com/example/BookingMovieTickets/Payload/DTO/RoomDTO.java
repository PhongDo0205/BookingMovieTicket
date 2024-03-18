package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class RoomDTO {
    private int id;
    private int capacity;
    private String type;
    private String description;
    private int cinemaId;
    private String code;
    private String name;
    private boolean isActive;
}
