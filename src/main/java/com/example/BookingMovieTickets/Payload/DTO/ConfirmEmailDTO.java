package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConfirmEmailDTO {
    private int id;
    private int userId;
    private LocalDateTime requiredDateTime;
    private LocalDateTime exquiredDateTime;
    private String confirmCode;
    private boolean isConfirm;
}
