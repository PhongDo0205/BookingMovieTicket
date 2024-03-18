package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "movieType")
public class MovieType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movieTypeName")
    private String movieTypeName;

    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "movieTypeofMovie")
    @JsonManagedReference
    private Set<Movie> setMovieOfMovieTypes;
}
