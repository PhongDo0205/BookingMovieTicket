package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDTO {
    private int id;
    private double price;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String code;
    private int movieId;
    private String name;
    private int roomId;
    private int isActive;
}
