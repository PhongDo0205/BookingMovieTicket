package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {
}
