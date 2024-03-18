package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {
    Promotion findByName(String promotionName);
}
