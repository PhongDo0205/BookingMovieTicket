package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    @Query("SELECT s FROM Schedule s " +
            "WHERE s.roomId = :roomId " +
            "AND NOT ((s.startAt > :endAt) OR (s.endAt < :startAt))")
    List<Schedule> findByRoomIdAndTimeRange(int roomId, LocalDateTime startAt, LocalDateTime endAt);
//    @Query("SELECT s FROM Schedule s WHERE s.roomId = :roomId")
//    List<Schedule> findByRoomId(@Param("roomId") int roomId);
}
