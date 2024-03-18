package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "confirmEmail")
public class ConfirmEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @JsonBackReference
    private User userfromConfirmEmail;
    @Column(name = "userId")
    private int userId;

    @Column(name = "requiredDateTime")
    private LocalDateTime requiredDateTime;

    @Column(name = "exquiredDateTime")
    private LocalDateTime exquiredDateTime;

    @Column(name = "confirmCode")
    private String confirmCode;

    @Column(name = "isConfirm")
    private boolean isConfirm;
}
