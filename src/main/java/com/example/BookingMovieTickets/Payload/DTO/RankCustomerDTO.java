package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class RankCustomerDTO {
    private int id;
    private int point;
    private String description;
    private String name;
    private boolean isActive;
}
