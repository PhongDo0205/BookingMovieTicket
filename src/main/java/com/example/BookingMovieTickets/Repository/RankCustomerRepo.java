package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.RankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankCustomerRepo extends JpaRepository<RankCustomer, Integer> {
}
