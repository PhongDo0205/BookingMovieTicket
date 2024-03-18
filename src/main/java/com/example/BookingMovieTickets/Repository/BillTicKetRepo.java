package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.BillTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTicKetRepo extends JpaRepository<BillTicket, Integer> {
    BillTicket findByTicketId(int id);
}
