package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Integer> {

    @Query("SELECT DISTINCT c FROM Cinema c " +
            "JOIN Room r ON c.id = r.cinemaId " +
            "JOIN Schedule s ON r.id = s.roomId " +
            "WHERE s.movieId = :movieId")
    List<Cinema> findCinemasByMovie(int movieId);

    @Query("SELECT SUM(bt.quantity * t.priceTicket) " +
            "FROM Cinema c " +
            "JOIN Room r ON c.id = r.cinemaId " +
            "JOIN Schedule s ON r.id = s.roomId " +
            "JOIN Ticket t ON s.id = t.scheduleId " +
            "JOIN BillTicket bt ON t.id = bt.ticketId " +
            "JOIN Bill b ON bt.billId = b.id " +
            "WHERE c.Id = :cinemaId " +
            "AND b.createTime BETWEEN :startTime AND :endTime")
    Double getCinemaRevenueBetween(@Param("cinemaId") int cinemaId,
                                   @Param("startTime") LocalDateTime startTime,
                                   @Param("endTime") LocalDateTime endTime);
}

