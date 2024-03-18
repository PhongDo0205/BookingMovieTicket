package com.example.BookingMovieTickets.Payload.Request.User;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private int point;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private boolean isActive;
}
