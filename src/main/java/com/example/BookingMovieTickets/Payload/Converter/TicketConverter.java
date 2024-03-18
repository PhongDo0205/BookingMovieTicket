package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Ticket;
import com.example.BookingMovieTickets.Payload.DTO.TicketDTO;
import com.example.BookingMovieTickets.Payload.Request.Ticket.AddTicketRequest;
import com.example.BookingMovieTickets.Payload.Request.Ticket.UpdateTicketRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketConverter {

    public TicketDTO entityToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setCode(ticket.getCode());
        ticketDTO.setScheduleId(ticket.getScheduleId());
        ticketDTO.setSeatId(ticket.getSeatId());
        ticketDTO.setActive(true);
        ticketDTO.setPriceTicket(ticket.getPriceTicket());
        return ticketDTO;
    }

    public List<TicketDTO> entityToDTOList(List<Ticket> ticketList){
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDTOList.add(entityToDTO(ticket));
        }
        return ticketDTOList;
    }

    public Ticket addTicketFromRequest(AddTicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setCode(request.getCode());
        ticket.setScheduleId(request.getScheduleId());
        ticket.setSeatId(request.getSeatId());
        ticket.setActive(request.isActive());
        ticket.setPriceTicket(request.getPriceTicket());
        return ticket;
    }

    public Ticket updateTicketFromRequest(UpdateTicketRequest request, Ticket existingTicket) {
        if (request.getCode() != null) {
            existingTicket.setCode(request.getCode());
        }
        if (request.getScheduleId() > 0) {
            existingTicket.setScheduleId(request.getScheduleId());
        }
        if (request.getSeatId() > 0) {
            existingTicket.setSeatId(request.getSeatId());
        }
        if (request.getPriceTicket() > 0) {
            existingTicket.setPriceTicket(request.getPriceTicket());
        }
        return existingTicket;
    }
}

