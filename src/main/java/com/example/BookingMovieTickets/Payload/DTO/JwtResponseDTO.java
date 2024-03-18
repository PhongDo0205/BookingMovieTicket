package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponseDTO {
    private String accessToken;
}
