package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class BillTicketDTO {
    private int id;
    private int quantity;
    private int billId;
    private int ticketId;
}
