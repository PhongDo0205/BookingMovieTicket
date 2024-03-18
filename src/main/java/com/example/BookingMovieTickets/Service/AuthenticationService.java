package com.example.BookingMovieTickets.Service;

import com.example.BookingMovieTickets.Payload.DTO.UserDTO;
import com.example.BookingMovieTickets.Payload.Request.User.CreateUserRequest;
import com.example.BookingMovieTickets.Payload.Request.User.LoginRequest;
import com.example.BookingMovieTickets.Payload.Respond.JwtAuthenticationResponse;
import com.example.BookingMovieTickets.Payload.Respond.ResponseObject;

public interface AuthenticationService {
    ResponseObject<UserDTO> signup(CreateUserRequest request);

    void sendConfirmationEmail(UserDTO userDTO);

    JwtAuthenticationResponse signin(LoginRequest request);

    ResponseObject<UserDTO> forgotPassword(String username);

    ResponseObject<UserDTO> changePassword(String usename, String confirmCode, String newpassword);
}
