package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GeneralSettingDTO {
    private int id;
    private LocalDateTime breakTime;
    private int businessHours;
    private LocalDateTime closeTime;
    private double fixedTicketPrice;
    private int percentDay;
    private int percentWeekend;
    private LocalDateTime timeBeginToChange;
}
