package com.example.BookingMovieTickets.Controller;

import com.example.BookingMovieTickets.Payload.DTO.*;
import com.example.BookingMovieTickets.Payload.Request.User.UpdateProfileRequest;
import com.example.BookingMovieTickets.Payload.Respond.ResponseObject;
import com.example.BookingMovieTickets.Service.AuthenticationService;
import com.example.BookingMovieTickets.Service.Impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserServiceImpl userService;
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "/confirm-email", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseObject<ConfirmEmailDTO> confirmEmail(@RequestParam(required = false) String confirmCode) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.confirmEmail(userDetails.getUsername(), confirmCode);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/authorization", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Public Endpoint - Authorization Required");
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/details")
    public ResponseEntity<String> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok("Hello, " + userDetails.getUsername() + "! Your authorities: " + userDetails.getAuthorities());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-top-movie")
    ResponseObject<List<MovieDTO>> getTopMovie(@RequestParam int pageSize, @RequestParam int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userService.getTopMovie(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-movie-by-room-and-seatstatus")
    ResponseObject<List<MovieDTO>> getMoviesByCinemaRoomAndSeatStatus(@RequestParam int cinemaId,@RequestParam int roomId,@RequestParam int seatStatusId){
        return userService.getMoviesByCinemaRoomAndSeatStatus(cinemaId, roomId, seatStatusId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-movie")
    public ResponseObject<List<MovieDTO>> getAllMovies(@RequestParam int pageSize, @RequestParam int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userService.getAllMovie(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-cinema-have-movie")
    public ResponseObject<List<CinemaDTO>> getAllCinemaHaveMovie(@RequestParam int movieId) {
        return userService.getAllCinemaHaveMovie(movieId);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-room-in-cinema")
    public ResponseObject<List<RoomDTO>> getAllRoomsInCinema(@RequestParam int cinemaId, @RequestParam int movieId) {
        return userService.getAllRoomInCinema(cinemaId, movieId);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-schedule-by-room")
    public ResponseObject<List<ScheduleDTO>> getAllSchedulesByRoom(@RequestParam int roomId) {
        return userService.getAllScheduleByRoom(roomId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-available-ticket-by-schedule")
    public ResponseObject<List<TicketDTO>> getAllTicketBySchedule(@RequestParam int scheduleId){
        return userService.getAllTicketBySchedule(scheduleId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-food")
    public ResponseObject<List<FoodDTO>> getAllFoods(@RequestParam int pageSize, @RequestParam int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userService.getAllFood(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "/create-bill", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<BillDTO> createBill(
            @RequestParam int scheduleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.createBill(userDetails.getUsername(), scheduleId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "/process-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<String> processPayment(
            HttpServletRequest request,
            @RequestParam int billId,
            @RequestParam String paymentMethod) throws IOException {
        return userService.processPayment(billId, paymentMethod, request);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-promotion-by-name")
    public ResponseObject<PromotionDTO> getPromotionByName(@RequestParam String promotionName) {
        return userService.getPromotionByName(promotionName);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-promotion")
    public ResponseObject<List<PromotionDTO>> getAllPromotion(@RequestParam int pageSize, @RequestParam int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userService.getAllPromotion(pageable);
    }

    // BillFood
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-bill-food-by-id")
    public ResponseObject<BillFoodDTO> getBillFoodById(@RequestParam int billFoodId) {
        return userService.getBillFoodById(billFoodId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-bill-food")
    public ResponseObject<List<BillFoodDTO>> getAllBillFood(@RequestParam int pageSize, @RequestParam int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userService.getAllBillFood(pageable);
    }

    // BillTicket
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-bill-ticket-by-id")
    public ResponseObject<BillTicketDTO> getBillTicketById(@RequestParam int billTicketId) {
        return userService.getBillTicketById(billTicketId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-bill-ticket")
    public ResponseObject<List<BillTicketDTO>> getAllBillTicket(@RequestParam int pageSize, @RequestParam int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userService.getAllBillTicket(pageable);
    }

    // Profile
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/update-profile")
    public ResponseObject<UserDTO> updateProfile(@RequestBody UpdateProfileRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.updateProfile(userDetails.getUsername(), request);
    }

    // Ticket
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-all-ticket")
    public ResponseObject<List<TicketDTO>> getAllTicket(@RequestParam int pageSize, @RequestParam int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userService.getAllTicket(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/get-ticket-by-id")
    public ResponseObject<TicketDTO> getTicketById(@RequestParam int ticketId) {
        return userService.getTicketById(ticketId);
    }

}
