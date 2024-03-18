package com.example.BookingMovieTickets.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "generalSetting")
public class GeneralSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "breakTime")
    private LocalDateTime breakTime;

    @Column(name = "businessHours")
    private int businessHours;

    @Column(name = "closeTime")
    private LocalDateTime closeTime;

    @Column(name = "fixedTicketPrice")
    private double fixedTicketPrice;

    @Column(name = "percentDay")
    private int percentDay;

    @Column(name = "percentWeekend")
    private int percentWeekend;

    @Column(name = "timeBeginToChange")
    private LocalDateTime timeBeginToChange;
}
