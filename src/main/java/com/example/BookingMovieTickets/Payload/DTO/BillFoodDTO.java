package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

@Data
public class BillFoodDTO {
    private int id;
    private int quantity;
    private int billId;
    private int foodId;
}
