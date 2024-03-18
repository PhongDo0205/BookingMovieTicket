package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.ConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmEmailRepo extends JpaRepository<ConfirmEmail, Integer> {

    ConfirmEmail findByUserIdAndConfirmCode(int userId, String confirmCode);
    ConfirmEmail findByUserId(int userId);
    ConfirmEmail findByConfirmCode(String confirmCode);
}
