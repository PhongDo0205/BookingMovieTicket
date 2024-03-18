package com.example.BookingMovieTickets.Controller;

import com.example.BookingMovieTickets.Payload.DTO.UserDTO;
import com.example.BookingMovieTickets.Payload.Request.User.CreateUserRequest;
import com.example.BookingMovieTickets.Payload.Request.User.LoginRequest;
import com.example.BookingMovieTickets.Payload.Respond.JwtAuthenticationResponse;
import com.example.BookingMovieTickets.Payload.Respond.ResponseObject;
import com.example.BookingMovieTickets.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<UserDTO> signup(@RequestBody CreateUserRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Public Endpoint - No Authentication Required");
    }

    @GetMapping(value = "/forgot-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<UserDTO> forgotPassword(@RequestParam String username) {
        return authenticationService.forgotPassword(username);
    }

    @PostMapping(value = "/change-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<UserDTO> changePassword(@RequestParam String username, @RequestParam String confirmCode, @RequestParam String newpassword) {
        return authenticationService.changePassword(username, confirmCode, newpassword);
    }
}
