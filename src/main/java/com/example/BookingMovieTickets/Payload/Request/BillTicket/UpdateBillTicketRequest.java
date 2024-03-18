package com.example.BookingMovieTickets.Payload.Request.BillTicket;

import lombok.Data;

@Data
public class UpdateBillTicketRequest {
    private int id;
    private int quantity;
    private int billId;
    private int ticketId;
}
