package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    @Column(name = "startAt")
    private LocalDateTime startAt;

    @Column(name = "endAt")
    private LocalDateTime endAt;

    private String code;

    @ManyToOne
    @JoinColumn(name = "movieId", insertable = false, updatable = false)
    @JsonBackReference
    private Movie movieSchedule;
    @Column(name = "movieId")
    private int movieId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "roomId", insertable = false, updatable = false)
    @JsonBackReference
    private Room roomSchedule;
    @Column(name = "roomId")
    private int roomId;

    @Column(name = "isActive")
    private int isActive;

    @OneToMany(mappedBy = "scheduleofTicket")
    @JsonManagedReference
    private Set<Ticket> tickets;
}
