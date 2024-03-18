package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "billTicket")
public class BillTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "billId", insertable = false, updatable = false)
    @JsonBackReference
    private Bill billfromBillTicket;
    @Column(name = "billId")
    private int billId;

    @ManyToOne
    @JoinColumn(name = "ticketId", insertable = false, updatable = false)
    @JsonBackReference
    private Ticket ticketfromBillTicket;
    @Column(name = "ticketId")
    private int ticketId;
}
