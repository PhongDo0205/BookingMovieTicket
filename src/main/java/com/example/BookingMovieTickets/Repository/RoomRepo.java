package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    @Query("SELECT DISTINCT r FROM Room r " +
            "JOIN Schedule s ON r.id = s.roomId " +
            "WHERE r.cinemaId = :cinemaId " +
            "AND s.movieId = :movieId")
    List<Room> findRoomsByCinemaAndMovie(int cinemaId, int movieId);

}
