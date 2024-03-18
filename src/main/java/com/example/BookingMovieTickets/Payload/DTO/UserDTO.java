package com.example.BookingMovieTickets.Payload.DTO;

import com.example.BookingMovieTickets.Entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private int point;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private int rankCustomerId;
    private int userStatusId;
    private boolean isActive;
    private int roleId;
//    private Role role;
}
