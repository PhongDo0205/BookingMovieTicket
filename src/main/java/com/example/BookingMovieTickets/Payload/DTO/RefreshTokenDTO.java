package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefreshTokenDTO {
    private int id;
    private String token;
    private LocalDateTime expiredTime;
    private int userId;
}
