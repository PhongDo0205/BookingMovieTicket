package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.BillTicket;
import com.example.BookingMovieTickets.Payload.DTO.BillTicketDTO;
import com.example.BookingMovieTickets.Payload.Request.BillTicket.AddBillTicketRequest;
import com.example.BookingMovieTickets.Payload.Request.BillTicket.UpdateBillTicketRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillTicketConverter {

    public BillTicketDTO entityToDTO(BillTicket billTicket) {
        BillTicketDTO billTicketDTO = new BillTicketDTO();
        billTicketDTO.setId(billTicket.getId());
        billTicketDTO.setQuantity(billTicket.getQuantity());
        billTicketDTO.setBillId(billTicket.getBillId());
        billTicketDTO.setTicketId(billTicket.getTicketId());
        return billTicketDTO;
    }

    public List<BillTicketDTO> entityToDTOList(List<BillTicket> billTicketList){
        List<BillTicketDTO> billTicketDTOList = new ArrayList<>();
        for (BillTicket billTicket : billTicketList) {
            billTicketDTOList.add(entityToDTO(billTicket));
        }
        return billTicketDTOList;
    }

    public BillTicket addBillTicketFromRequest(AddBillTicketRequest request) {
        BillTicket billTicket = new BillTicket();
        billTicket.setQuantity(request.getQuantity());
        billTicket.setBillId(request.getBillId());
        billTicket.setTicketId(request.getTicketId());
        return billTicket;
    }

    public BillTicket updateBillTicketFromRequest(UpdateBillTicketRequest request, BillTicket existingBillTicket) {
        if (request.getId() != 0) {
            existingBillTicket.setId(request.getId());
        }
        if (request.getQuantity() > 0) {
            existingBillTicket.setQuantity(request.getQuantity());
        }
        if (request.getBillId() > 0) {
            existingBillTicket.setBillId(request.getBillId());
        }
        if (request.getTicketId() > 0) {
            existingBillTicket.setTicketId(request.getTicketId());
        }
        return existingBillTicket;
    }
}

