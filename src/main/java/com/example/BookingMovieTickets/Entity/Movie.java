package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movieDuration")
    private int movieDuration;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "premiereDate")
    private LocalDateTime premiereDate;

    private String description;

    private String director;

    private String image;

    private String language;

    @ManyToOne
    @JoinColumn(name = "movieTypeId", insertable = false, updatable = false)
    @JsonBackReference
    private MovieType movieTypeofMovie;
    @Column(name = "movieTypeId")
    private int movieTypeId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "rateId", insertable = false, updatable = false)
    @JsonBackReference
    private Rate rateofMovie;
    @Column(name = "rateId")
    private int rateId;

    private String trailer;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "heroImage")
    private String heroImage;

    @OneToMany(mappedBy = "movieSchedule")
    @JsonManagedReference
    private Set<Schedule> schedules;
}
