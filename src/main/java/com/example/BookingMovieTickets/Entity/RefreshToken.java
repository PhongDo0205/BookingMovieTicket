package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "refreshToken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    @Column(name = "expiredTime")
    private LocalDateTime expiredTime;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @JsonBackReference
    private User userfromRefreshToken;
    @Column(name = "userId")
    private int userId;
}
