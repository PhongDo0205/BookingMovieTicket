package com.example.BookingMovieTickets.Payload.Request.User;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
