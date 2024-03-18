package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {
    @Query("SELECT b FROM Bill b WHERE b.createTime BETWEEN :startTime AND :endTime")
    List<Bill> findByCreateTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
