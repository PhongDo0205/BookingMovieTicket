package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private String description;

    private String code;

    @Column(name = "nameOfCinema")
    private String nameOfCinema;

    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "cinemahaveRoom")
    @JsonManagedReference
    private Set<Room> rooms;
}
