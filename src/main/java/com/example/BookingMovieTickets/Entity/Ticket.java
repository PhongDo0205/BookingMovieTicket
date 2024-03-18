package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "scheduleId", insertable = false, updatable = false)
    @JsonBackReference
    private Schedule scheduleofTicket;
    @Column(name = "scheduleId")
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "seatId", insertable = false, updatable = false)
    @JsonBackReference
    private Seat seataccordingtoTicket;
    @Column(name = "seatId")
    private int seatId;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "priceTicket")
    private double priceTicket;

    @OneToMany(mappedBy = "ticketfromBillTicket")
    @JsonManagedReference
    private Set<BillTicket> billTickets;
}
