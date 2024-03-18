package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Room;
import com.example.BookingMovieTickets.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer> {
//    @Query("SELECT DISTINCT se FROM Seat se " +
//            "JOIN Room r ON se.roomId = r.id " +
//            "WHERE se.roomId = :roomId " +
//            "AND se.seatStatusId = :seatStatusId")
//    List<Seat> findSeatsByRoomAndSeatStatus(@Param("roomId") int roomId,
//                                            @Param("seatStatusId") int seatStatusId);
}
