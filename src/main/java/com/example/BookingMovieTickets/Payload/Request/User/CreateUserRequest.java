package com.example.BookingMovieTickets.Payload.Request.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String name;
    private String password;
    private String email;
    private String phoneNumber;

}
