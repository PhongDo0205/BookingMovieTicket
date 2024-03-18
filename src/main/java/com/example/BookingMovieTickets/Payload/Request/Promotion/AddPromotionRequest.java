package com.example.BookingMovieTickets.Payload.Request.Promotion;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddPromotionRequest {
    private int percent;
    private int quantity;
    private String type;
    private String description;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private String name;
    private int rankCustomerId;
}
