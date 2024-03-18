package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    @ManyToOne
    @JoinColumn(name = "seatStatusId", insertable = false, updatable = false)
    @JsonBackReference
    private SeatStatus seatStatusofSeat;
    @Column(name = "seatStatusId")
    private int seatStatusId;

    private String line;

    @ManyToOne
    @JoinColumn(name = "roomId", insertable = false, updatable = false)
    @JsonBackReference
    private Room roomSeat;
    @Column(name = "roomId")
    private int roomId;

    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "seatTypeId", insertable = false, updatable = false)
    @JsonBackReference
    private SeatType seatTypeofSeat;
    @Column(name = "seatTypeId")
    private int seatTypeId;

    @OneToMany(mappedBy = "seataccordingtoTicket")
    @JsonManagedReference
    private Set<Ticket> tickets;
}
