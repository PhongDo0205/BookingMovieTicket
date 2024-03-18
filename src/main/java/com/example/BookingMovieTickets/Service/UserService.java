package com.example.BookingMovieTickets.Service;

import com.example.BookingMovieTickets.Payload.DTO.*;
import com.example.BookingMovieTickets.Payload.Request.User.UpdateProfileRequest;
import com.example.BookingMovieTickets.Payload.Respond.ResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface UserService {
    ResponseObject<ConfirmEmailDTO> confirmEmail(String username, String confirmCode);

    ResponseObject<UserDTO> getUserById(int id);

    ResponseObject<List<UserDTO>> getAllUser(Pageable pageable);

    ResponseObject<List<MovieDTO>> getTopMovie(Pageable pageable);

    ResponseObject<List<MovieDTO>> getMoviesByCinemaRoomAndSeatStatus(int cinemaId, int roomId, int seatStatusId);

    ResponseObject<List<MovieDTO>> getAllMovie(Pageable pageable);

    ResponseObject<List<CinemaDTO>> getAllCinemaHaveMovie(int movieId);

    ResponseObject<List<RoomDTO>> getAllRoomInCinema(int cinemaId, int movieId);

    ResponseObject<List<ScheduleDTO>> getAllScheduleByRoom(int roomId);

    ResponseObject<List<TicketDTO>> getAllTicketBySchedule(int scheduleId);

    ResponseObject<List<FoodDTO>> getAllFood(Pageable pageable);

    ResponseObject<BillDTO> createBill(String username, int scheduleId);

//    ResponseObject<String> processPayment(int billId, String paymentMethod);

    ResponseObject<String> processPayment(int billId, String paymentMethod, HttpServletRequest request) throws IOException;

    ResponseObject<List<PromotionDTO>> getAllPromotion(Pageable pageable);

    ResponseObject<PromotionDTO> getPromotionByName(String promotionName);

    ResponseObject<BillFoodDTO> getBillFoodById(int billFoodId);

    ResponseObject<List<BillFoodDTO>> getAllBillFood(Pageable pageable);

    ResponseObject<BillTicketDTO> getBillTicketById(int billTicketId);

    ResponseObject<List<BillTicketDTO>> getAllBillTicket(Pageable pageable);

    ResponseObject<UserDTO> updateProfile(String username, UpdateProfileRequest request);

    ResponseObject<List<TicketDTO>> getAllTicket(Pageable pageable);

    ResponseObject<TicketDTO> getTicketById(int ticketId);
}
