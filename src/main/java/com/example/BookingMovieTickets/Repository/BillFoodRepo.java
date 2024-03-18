package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.BillFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillFoodRepo extends JpaRepository<BillFood, Integer> {
    List<BillFood> findByBillId(int id);
}
