package com.example.BookingMovieTickets.Payload.Request.BillFood;

import lombok.Data;

@Data
public class UpdateBillFoodRequest {
    private int id;
    private int quantity;
    private int billId;
    private int foodId;
}
