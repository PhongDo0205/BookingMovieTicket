package com.example.BookingMovieTickets.Service;

import com.example.BookingMovieTickets.Payload.DTO.*;
import com.example.BookingMovieTickets.Payload.Request.BillFood.AddBillFoodRequest;
import com.example.BookingMovieTickets.Payload.Request.BillFood.UpdateBillFoodRequest;
import com.example.BookingMovieTickets.Payload.Request.BillTicket.AddBillTicketRequest;
import com.example.BookingMovieTickets.Payload.Request.BillTicket.UpdateBillTicketRequest;
import com.example.BookingMovieTickets.Payload.Request.Cinema.AddCinemaRequest;
import com.example.BookingMovieTickets.Payload.Request.Cinema.UpdateCinemaRequest;
import com.example.BookingMovieTickets.Payload.Request.Food.AddFoodRequest;
import com.example.BookingMovieTickets.Payload.Request.Food.UpdateFoodRequest;
import com.example.BookingMovieTickets.Payload.Request.Movie.AddMovieRequest;
import com.example.BookingMovieTickets.Payload.Request.Movie.UpdateMovieRequest;
import com.example.BookingMovieTickets.Payload.Request.Promotion.AddPromotionRequest;
import com.example.BookingMovieTickets.Payload.Request.Promotion.UpdatePromotionRequest;
import com.example.BookingMovieTickets.Payload.Request.Room.AddRoomRequest;
import com.example.BookingMovieTickets.Payload.Request.Room.UpdateRoomRequest;
import com.example.BookingMovieTickets.Payload.Request.Schedule.AddScheduleRequest;
import com.example.BookingMovieTickets.Payload.Request.Schedule.UpdateScheduleRequest;
import com.example.BookingMovieTickets.Payload.Request.Seat.AddSeatRequest;
import com.example.BookingMovieTickets.Payload.Request.Seat.UpdateSeatRequest;
import com.example.BookingMovieTickets.Payload.Request.Ticket.AddTicketRequest;
import com.example.BookingMovieTickets.Payload.Request.Ticket.UpdateTicketRequest;
import com.example.BookingMovieTickets.Payload.Respond.ResponseObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AdminService {
    // Cinema
    ResponseObject<CinemaDTO> addCinema(AddCinemaRequest request);
    ResponseObject<CinemaDTO> updateCinema(UpdateCinemaRequest request);
    ResponseObject<CinemaDTO> deleteCinema(int id);

    // Room
    ResponseObject<RoomDTO> addRoom(AddRoomRequest request);
    ResponseObject<RoomDTO> updateRoom(UpdateRoomRequest request);
    ResponseObject<RoomDTO> deleteRoom(int id);

    // Seat
    ResponseObject<SeatDTO> addSeat(AddSeatRequest request);
    ResponseObject<SeatDTO> updateSeat(UpdateSeatRequest request);
    ResponseObject<SeatDTO> deleteSeat(int id);

    // Food
    ResponseObject<FoodDTO> addFood(AddFoodRequest request);
    ResponseObject<FoodDTO> updateFood(UpdateFoodRequest request);
    ResponseObject<FoodDTO> deleteFood(int id);
    ResponseObject<MovieDTO> addMovie(AddMovieRequest request);

    ResponseObject<MovieDTO> updateMovie(UpdateMovieRequest request);

    ResponseObject<ScheduleDTO> updateSchedule(UpdateScheduleRequest request);

    ResponseObject<MovieDTO> deleteMovie(int id);

    ResponseObject<ScheduleDTO> addSchedule(AddScheduleRequest request);

    ResponseObject<ScheduleDTO> deleteSchedule(int id);

    ResponseObject<PromotionDTO> addPromotion(AddPromotionRequest request);

    ResponseObject<PromotionDTO> updatePromotion(UpdatePromotionRequest request);

    ResponseObject<PromotionDTO> deletePromotion(String name);

    ResponseObject<BillFoodDTO> addBillFood(AddBillFoodRequest request);

    ResponseObject<BillFoodDTO> updateBillFood(UpdateBillFoodRequest request);

    ResponseObject<BillFoodDTO> deleteBillFood(int id);

    ResponseObject<BillTicketDTO> addBillTicket(AddBillTicketRequest request);

    ResponseObject<BillTicketDTO> updateBillTicket(UpdateBillTicketRequest request);

    ResponseObject<BillTicketDTO> deleteBillTicket(int id);

    ResponseObject<TicketDTO> addTicket(AddTicketRequest request);

    ResponseObject<TicketDTO> updateTicket(UpdateTicketRequest request);

    ResponseObject<TicketDTO> deleteTicket(int ticketId);

    ResponseObject<List<Map<String, Object>>> getCinemaRevenueStatistics(LocalDateTime startTime, LocalDateTime endTime);

    ResponseObject<List<FoodDTO>> getTopSellingFoodsLast7Days(LocalDateTime startTime, LocalDateTime endTime);
}
