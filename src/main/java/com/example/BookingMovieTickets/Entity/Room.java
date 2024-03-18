package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int capacity;

    private String type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "cinemaId", insertable = false, updatable = false)
    @JsonBackReference
    private Cinema cinemahaveRoom;
    @Column(name = "cinemaId")
    private int cinemaId;

    private String code;

    private String name;

    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "roomSchedule")
    @JsonManagedReference
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "roomSeat")
    @JsonManagedReference
    private Set<Seat> seats;
}
