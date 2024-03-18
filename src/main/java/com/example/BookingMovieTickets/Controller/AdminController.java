package com.example.BookingMovieTickets.Controller;

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
import com.example.BookingMovieTickets.Service.Impl.AdminServiceImpl;
import com.example.BookingMovieTickets.Service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get-user-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<UserDTO> getUserById(@RequestParam int id){
        return userService.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all-user")
    public ResponseObject<List<UserDTO>> getAllUser(@RequestParam int pageSize, @RequestParam int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userService.getAllUser(pageable);
    }

    // Cinema
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-cinema", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseObject<CinemaDTO> addCinema(@RequestBody AddCinemaRequest request){
        return adminService.addCinema(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-cinema", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseObject<CinemaDTO> updateCinema(@RequestBody UpdateCinemaRequest request){
        return adminService.updateCinema(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-cinema")
    ResponseObject<CinemaDTO> deleteCinema(@RequestParam int id){
        return adminService.deleteCinema(id);
    }


    // Room
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-room", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<RoomDTO> addRoom(@RequestBody AddRoomRequest request) {
        return adminService.addRoom(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-room", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<RoomDTO> updateRoom(@RequestBody UpdateRoomRequest request) {
        return adminService.updateRoom(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-room")
    public ResponseObject<RoomDTO> deleteRoom(@RequestParam int id) {
        return adminService.deleteRoom(id);
    }

    // Seat
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-seat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<SeatDTO> addSeat(@RequestBody AddSeatRequest request) {
        return adminService.addSeat(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-seat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<SeatDTO> updateSeat(@RequestBody UpdateSeatRequest request) {
        return adminService.updateSeat(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-seat")
    public ResponseObject<SeatDTO> deleteSeat(@RequestParam int id) {
        return adminService.deleteSeat(id);
    }

    // Food
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-food", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<FoodDTO> addFood(@RequestBody AddFoodRequest request) {
        return adminService.addFood(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-food", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<FoodDTO> updateFood(@RequestBody UpdateFoodRequest request) {
        return adminService.updateFood(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-food")
    public ResponseObject<FoodDTO> deleteFood(@RequestParam int id) {
        return adminService.deleteFood(id);
    }

    // Movie
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<MovieDTO> addMovie(@RequestBody AddMovieRequest request) {
        return adminService.addMovie(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<MovieDTO> updateMovie(@RequestBody UpdateMovieRequest request) {
        return adminService.updateMovie(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-movie")
    public ResponseObject<MovieDTO> deleteMovie(@RequestParam int id) {
        return adminService.deleteMovie(id);
    }


    // Schedule
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<ScheduleDTO> addSchedule(@RequestBody AddScheduleRequest request) {
        return adminService.addSchedule(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<ScheduleDTO> updateSchedule(@RequestBody UpdateScheduleRequest request) {
        return adminService.updateSchedule(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-schedule")
    public ResponseObject<ScheduleDTO> deleteSchedule(@RequestParam int id) {
        return adminService.deleteSchedule(id);
    }

    // Promotion
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-promotion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<PromotionDTO> addPromotion(@RequestBody AddPromotionRequest request) {
        return adminService.addPromotion(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-promotion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<PromotionDTO> updatePromotion(@RequestBody UpdatePromotionRequest request) {
        return adminService.updatePromotion(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-promotion")
    public ResponseObject<PromotionDTO> deletePromotion(@RequestParam String name) {
        return adminService.deletePromotion(name);
    }

    // BillFood
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-bill-food", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<BillFoodDTO> addBillFood(@RequestBody AddBillFoodRequest request) {
        return adminService.addBillFood(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-bill-food", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<BillFoodDTO> updateBillFood(@RequestBody UpdateBillFoodRequest request) {
        return adminService.updateBillFood(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-bill-food")
    public ResponseObject<BillFoodDTO> deleteBillFood(@RequestParam int id) {
        return adminService.deleteBillFood(id);
    }

    // BillTicket
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-bill-ticket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<BillTicketDTO> addBillTicket(@RequestBody AddBillTicketRequest request) {
        return adminService.addBillTicket(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-bill-ticket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<BillTicketDTO> updateBillTicket(@RequestBody UpdateBillTicketRequest request) {
        return adminService.updateBillTicket(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-bill-ticket")
    public ResponseObject<BillTicketDTO> deleteBillTicket(@RequestParam int id) {
        return adminService.deleteBillTicket(id);
    }

    // Ticket
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-ticket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<TicketDTO> addTicket(@RequestBody AddTicketRequest request) {
        return adminService.addTicket(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-ticket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<TicketDTO> updateTicket(@RequestBody UpdateTicketRequest request) {
        return adminService.updateTicket(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-ticket")
    public ResponseObject<TicketDTO> deleteTicket(@RequestParam int ticketId) {
        return adminService.deleteTicket(ticketId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/revenue-statistics")
    public ResponseObject<List<Map<String, Object>>> getCinemaRevenueStatistics(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return adminService.getCinemaRevenueStatistics(startTime, endTime);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/top-selling-last-7-days")
    public ResponseObject<List<FoodDTO>> getTopSellingFoodsLast7Days(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return adminService.getTopSellingFoodsLast7Days(startTime, endTime);
    }

}
