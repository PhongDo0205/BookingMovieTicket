package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
//    @Query("SELECT t FROM Ticket t WHERE t.seatId IN :seatIds AND t.seatStatusId IN :seatStatusIds")
//    List<Ticket> findBySeatIdAndSeatStatusIdIn(
//            @Param("seatIds") List<Integer> seatIds,
//            @Param("seatStatusIds") List<Integer> seatStatusIds
//    );
}
