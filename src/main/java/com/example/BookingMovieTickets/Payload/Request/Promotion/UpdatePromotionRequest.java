package com.example.BookingMovieTickets.Payload.Request.Promotion;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdatePromotionRequest {
    private int id;
    private int percent;
    private int quantity;
    private String type;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private String description;
    private String name;
    private boolean isActive;
    private int rankCustomerId;
}
