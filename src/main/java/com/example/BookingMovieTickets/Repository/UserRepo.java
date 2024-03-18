package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByName(String name);
    Optional<User> findByName(String name);

    User findById(int id);

}
