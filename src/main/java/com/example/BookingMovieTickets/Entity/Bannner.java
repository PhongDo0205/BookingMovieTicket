package com.example.BookingMovieTickets.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "banner")
public class Bannner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "imageUrl")
    private String imageUrl;

    private String title;
}
