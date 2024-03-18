package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Cinema;
import com.example.BookingMovieTickets.Payload.DTO.CinemaDTO;
import com.example.BookingMovieTickets.Payload.Request.Cinema.AddCinemaRequest;
import com.example.BookingMovieTickets.Payload.Request.Cinema.UpdateCinemaRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class CinemaConverter {
    public CinemaDTO entityToDTO(Cinema cinema) {
        CinemaDTO cinemaDTO = new CinemaDTO();
        cinemaDTO.setId(cinema.getId());
        cinemaDTO.setAddress(cinema.getAddress());
        cinemaDTO.setDescription(cinema.getDescription());
        cinemaDTO.setCode(cinema.getCode());
        cinemaDTO.setNameOfCinema(cinema.getNameOfCinema());
        cinemaDTO.setActive(cinema.isActive());
        return cinemaDTO;
    }

    public Cinema addCinemaFromRequest(AddCinemaRequest request) {
        Cinema cinema = new Cinema();
        cinema.setAddress(request.getAddress());
        cinema.setDescription(request.getDescription());
        cinema.setCode(request.getCode());
        cinema.setNameOfCinema(request.getNameOfCinema());
        cinema.setActive(request.isActive());
        return cinema;
    }

    public Cinema updateCinemaFromRequest(UpdateCinemaRequest request, Cinema existingCinema) {
        if(request.getId() == 0){
            return null;
        }else{
            existingCinema.setId(request.getId());
        }
        if (request.getAddress() != null) {
            existingCinema.setAddress(request.getAddress());
        }
        if (request.getDescription() != null) {
            existingCinema.setDescription(request.getDescription());
        }
        if (request.getCode() != null) {
            existingCinema.setCode(request.getCode());
        }
        if (request.getNameOfCinema() != null) {
            existingCinema.setNameOfCinema(request.getNameOfCinema());
        }
        existingCinema.setActive(request.isActive());
        return existingCinema;
    }


    public List<CinemaDTO> entityToDTOList(List<Cinema> cinemaList) {
        List<CinemaDTO> cinemaDTOList = new ArrayList<>();
        for (Cinema cinema : cinemaList) {
            cinemaDTOList.add(entityToDTO(cinema));
        }
        return cinemaDTOList;
    }
}
