package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FoodDTO {
    private int id;
    private double price;
    private String description;
    private String image;
    private String nameOfFood;
    private boolean isActive;
}
