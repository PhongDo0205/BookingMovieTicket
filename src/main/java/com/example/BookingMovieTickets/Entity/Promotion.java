package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int percent;

    private int quantity;

    private String type;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    private String description;

    private String name;

    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "rankCustomerId", insertable = false, updatable = false)
    @JsonBackReference
    private RankCustomer rankCustomerinPromotion;
    @Column(name = "rankCustomerId")
    private int rankCustomerId;

    @OneToMany(mappedBy = "promotionfromBill")
    @JsonManagedReference
    private Set<Bill> bills;
}
