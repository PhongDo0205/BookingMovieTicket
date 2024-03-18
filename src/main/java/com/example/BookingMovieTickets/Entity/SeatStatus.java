package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "seatStatus")
public class SeatStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    @Column(name = "nameStatus", insertable = false, updatable = false)
    private String nameStatus;

    @OneToMany(mappedBy = "seatStatusofSeat")
    @JsonManagedReference
    private Set<Seat> seats;
}
