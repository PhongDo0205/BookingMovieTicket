package com.example.BookingMovieTickets.Payload.Request.Schedule;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddScheduleRequest {
    private double price;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String code;
    private int movieId;
    private String name;
    private int roomId;
    private int isActive;
}

