package com.example.BookingMovieTickets.Repository;

import com.example.BookingMovieTickets.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findById(Integer integer);
}
