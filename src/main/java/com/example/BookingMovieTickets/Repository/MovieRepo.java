package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
    @Query("SELECT m " +
            "FROM Movie m " +
            "JOIN Schedule s ON m.id = s.movieId " +
            "JOIN Ticket t ON s.id = t.scheduleId " +
            "GROUP BY m.id " +
            "ORDER BY COUNT(t.id) DESC")
    Page<Movie> findTopMoviesByTicketSales(Pageable pageable);

    @Query("SELECT DISTINCT m FROM Movie m " +
            "JOIN Schedule s ON m.id = s.movieId " +
            "JOIN Room r ON s.roomId = r.id " +
            "JOIN Seat se ON r.id = se.roomId " +
            "WHERE r.cinemaId = :cinemaId " +
            "AND r.id = :roomId " +
            "AND se.seatStatusId = :seatStatusId")
    List<Movie> findMoviesByCinemaAndRoomAndSeatStatus(@Param("cinemaId") int cinemaId,
                                                       @Param("roomId") int roomId,
                                                       @Param("seatStatusId") int seatStatusId);

}
