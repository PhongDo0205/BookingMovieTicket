package com.example.BookingMovieTickets.Payload.Request.BillFood;

import lombok.Data;

@Data
public class AddBillFoodRequest {
    private int quantity;
    private int billId;
    private int foodId;
}
