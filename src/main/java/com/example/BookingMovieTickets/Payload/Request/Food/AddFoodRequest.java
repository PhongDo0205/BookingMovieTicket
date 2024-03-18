package com.example.BookingMovieTickets.Payload.Request.Food;

import lombok.Data;

@Data
public class AddFoodRequest {
    private double price;
    private String description;
    private String image;
    private String nameOfFood;
    private boolean isActive;
}
