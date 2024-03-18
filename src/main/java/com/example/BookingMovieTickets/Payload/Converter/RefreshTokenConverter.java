package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.RefreshToken;
import com.example.BookingMovieTickets.Entity.User;
import com.example.BookingMovieTickets.Payload.DTO.RefreshTokenDTO;
import com.example.BookingMovieTickets.Payload.DTO.UserDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class RefreshTokenConverter {
    public RefreshTokenDTO entityToDTO(RefreshToken refreshToken){
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
        refreshTokenDTO.setId(refreshToken.getId());
        refreshTokenDTO.setToken(refreshToken.getToken());
        refreshTokenDTO.setExpiredTime(refreshToken.getExpiredTime());
        refreshTokenDTO.setUserId(refreshToken.getUserId());
        return refreshTokenDTO;
    }

    public RefreshToken generateNewToken(String token, LocalDateTime expiredTime, int userId){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setExpiredTime(expiredTime);
        refreshToken.setUserId(userId);
        return refreshToken;
    }


}
