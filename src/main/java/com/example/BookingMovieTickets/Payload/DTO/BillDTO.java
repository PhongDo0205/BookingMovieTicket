package com.example.BookingMovieTickets.Payload.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BillDTO {
    private int id;
    private double totalMoney;
    private String tradingCode;
    private LocalDateTime createTime;
    private int customerId;
    private String name;
    private LocalDateTime updateTime;
    private int promotionId;
    private boolean isActive;
    private int billStatusId;
}
