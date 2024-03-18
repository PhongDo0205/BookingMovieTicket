package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    private String description;

    private String image;

    @Column(name = "nameOfFood")
    private String nameOfFood;

    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "foodfromBillFood")
    @JsonManagedReference
    private Set<BillFood> billFoods;
}
