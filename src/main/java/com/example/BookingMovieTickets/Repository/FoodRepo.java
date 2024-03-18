package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {
}
