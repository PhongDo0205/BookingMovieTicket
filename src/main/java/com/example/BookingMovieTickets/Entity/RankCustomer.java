package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "rankCustomer")
public class RankCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int point;

    private String description;

    private String name;

    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "rankCustomerinPromotion")
    @JsonManagedReference
    private Set<Promotion> promotions;

    @OneToMany(mappedBy = "rankCustomerofUser")
    @JsonManagedReference
    private Set<User> users;
}
