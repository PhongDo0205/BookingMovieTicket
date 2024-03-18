package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "billFood")
public class BillFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "billId", insertable = false, updatable = false)
    @JsonBackReference
    private Bill billfromBillFood;
    @Column(name = "billId")
    private int billId;

    @ManyToOne
    @JoinColumn(name = "foodId", insertable = false, updatable = false)
    @JsonBackReference
    private Food foodfromBillFood;
    @Column(name = "foodId")
    private int foodId;
}
