package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieDTO {
    private int id;
    private int movieDuration;
    private LocalDateTime endTime;
    private LocalDateTime premiereDate;
    private String description;
    private String director;
    private String image;
    private String language;
    private int movieTypeId;
    private String name;
    private int rateId;
    private String trailer;
    private boolean isActive;
    private String heroImage;
}
