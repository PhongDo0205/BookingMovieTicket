package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.ConfirmEmail;
import com.example.BookingMovieTickets.Payload.DTO.ConfirmEmailDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class ConfirmEmailConverter {

    public ConfirmEmailDTO entityToDTO(ConfirmEmail confirmEmail){
        ConfirmEmailDTO confirmEmailDTO = new ConfirmEmailDTO();
        confirmEmailDTO.setId(confirmEmail.getId());
        confirmEmailDTO.setUserId(confirmEmail.getUserId());
        confirmEmailDTO.setRequiredDateTime(confirmEmail.getRequiredDateTime());
        confirmEmailDTO.setExquiredDateTime(confirmEmail.getExquiredDateTime());
        confirmEmailDTO.setConfirmCode(confirmEmail.getConfirmCode());
        confirmEmailDTO.setConfirm(confirmEmail.isConfirm());
        return confirmEmailDTO;
    }

    public ConfirmEmail confirmEmail(int userId, String confirmCode){
        ConfirmEmail confirmEmail = new ConfirmEmail();
        confirmEmail.setUserId(userId);
        confirmEmail.setConfirm(true);
        confirmEmail.setRequiredDateTime(LocalDateTime.now());
        confirmEmail.setExquiredDateTime(LocalDateTime.now().plusMinutes(15));
        confirmEmail.setConfirmCode(confirmCode);
        return confirmEmail;
    }

}
