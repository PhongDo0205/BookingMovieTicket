package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private String code;

    @OneToMany(mappedBy = "rateofMovie")
    @JsonManagedReference
    private Set<Movie> setMoviesOfRates;
}
