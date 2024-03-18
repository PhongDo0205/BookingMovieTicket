package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "seatType")
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nameType")
    private String nameType;

    @OneToMany(mappedBy = "seatTypeofSeat")
    @JsonManagedReference
    private Set<Seat> seats;
}
