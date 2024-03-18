package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Seat;
import com.example.BookingMovieTickets.Payload.DTO.SeatDTO;
import com.example.BookingMovieTickets.Payload.Request.Seat.AddSeatRequest;
import com.example.BookingMovieTickets.Payload.Request.Seat.UpdateSeatRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SeatConverter {
    public SeatDTO entityToDTO(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(seat.getId());
        seatDTO.setNumber(seat.getNumber());
        seatDTO.setSeatStatusId(seat.getSeatStatusId());
        seatDTO.setLine(seat.getLine());
        seatDTO.setRoomId(seat.getRoomId());
        seatDTO.setActive(seat.isActive());
        seatDTO.setSeatTypeId(seat.getSeatTypeId());
        return seatDTO;
    }

    public Seat addSeatFromRequest(AddSeatRequest request) {
        Seat seat = new Seat();
        seat.setNumber(request.getNumber());
        seat.setSeatStatusId(request.getSeatStatusId());
        seat.setLine(request.getLine());
        seat.setRoomId(request.getRoomId());
        seat.setActive(request.isActive());
        seat.setSeatTypeId(request.getSeatTypeId());
        return seat;
    }

    public Seat updateSeatFromRequest(UpdateSeatRequest request, Seat existingSeat) {
        if(request.getId() == 0){
            return null;
        }else{
            existingSeat.setId(request.getId());
        }
        if (request.getNumber() > 0) {
            existingSeat.setNumber(request.getNumber());
        }
        if (request.getSeatStatusId() > 0) {
            existingSeat.setSeatStatusId(request.getSeatStatusId());
        }
        if (request.getLine() != null) {
            existingSeat.setLine(request.getLine());
        }
        if (request.getRoomId() > 0) {
            existingSeat.setRoomId(request.getRoomId());
        }
        existingSeat.setActive(request.isActive());
        if (request.getSeatTypeId() > 0) {
            existingSeat.setSeatTypeId(request.getSeatTypeId());
        }
        return existingSeat;
    }
}

