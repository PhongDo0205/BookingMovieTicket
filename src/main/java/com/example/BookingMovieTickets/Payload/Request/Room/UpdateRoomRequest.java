package com.example.BookingMovieTickets.Payload.Request.Room;

import lombok.Data;

@Data
public class UpdateRoomRequest {
    private int id;
    private int capacity;
    private String type;
    private String description;
    private int cinemaId;
    private String code;
    private String name;
    private boolean isActive;
}
