package com.example.BookingMovieTickets.Payload.Request.BillTicket;

import lombok.Data;

@Data
public class AddBillTicketRequest {
    private int quantity;
    private int billId;
    private int ticketId;
}
