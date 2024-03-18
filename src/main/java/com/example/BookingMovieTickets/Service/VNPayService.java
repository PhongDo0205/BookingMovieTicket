package com.example.BookingMovieTickets.Service;

import com.example.BookingMovieTickets.Payload.DTO.OrderRequestDTO;
import com.example.BookingMovieTickets.Payload.DTO.StatusRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface VNPayService {
    Map<String, Object> getStatus(HttpServletRequest request, StatusRequestDTO statusRequestDTO) throws IOException, IOException;

    Map<String, Object> createOrder(HttpServletRequest request, OrderRequestDTO orderRequestDTO) throws UnsupportedEncodingException;

    Map<String, String> getQueryUrl(Map<String, Object> payload) throws UnsupportedEncodingException;
}
