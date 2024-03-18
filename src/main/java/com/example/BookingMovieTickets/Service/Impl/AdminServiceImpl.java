package com.example.BookingMovieTickets.Service.Impl;

import com.example.BookingMovieTickets.Entity.*;
import com.example.BookingMovieTickets.Payload.Converter.*;
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
import com.example.BookingMovieTickets.Repository.*;
import com.example.BookingMovieTickets.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private CinemaRepo cinemaRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private SeatRepo seatRepo;
    @Autowired
    private FoodRepo foodRepo;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private BillTicKetRepo billTicKetRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;
    @Autowired
    private BillFoodRepo billFoodRepo;
    @Autowired
    private PromotionRepo promotionRepo;
    @Autowired
    private BillRepo billRepo;



    @Autowired
    private UserConverter usersConverter;
    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private CinemaConverter cinemaConverter;
    @Autowired
    private RoomConverter roomConverter;
    @Autowired
    private SeatConverter seatConverter;
    @Autowired
    private FoodConverter foodConverter;
    @Autowired
    private ScheduleConverter scheduleConverter;
    @Autowired
    private PromotionConverter promotionConverter;
    @Autowired
    private BillFoodConverter billFoodConverter;
    @Autowired
    private BillTicketConverter billTicketConverter;
    @Autowired
    private TicketConverter ticketConverter;


    @Autowired
    private ResponseObject<UserDTO> userDTOResponseObject;
    @Autowired
    private ResponseObject<CinemaDTO> cinemaDTOResponseObject;
    @Autowired
    private ResponseObject<RoomDTO> roomDTOResponseObject;
    @Autowired
    private ResponseObject<SeatDTO> seatDTOResponseObject;
    @Autowired
    private ResponseObject<FoodDTO> foodDTOResponseObject;
    @Autowired
    private ResponseObject<List<FoodDTO>> foodListResponseObject;
    @Autowired
    private ResponseObject<MovieDTO> movieDTOResponseObject;

    @Autowired
    private ResponseObject<List<UserDTO>> userListResponseObject;
    @Autowired
    private ResponseObject<List<MovieDTO>> movieListResponseObject;
    @Autowired
    private ResponseObject<ScheduleDTO> scheduleDTOResponseObject;
    ResponseObject<BillDTO> billDTOResponseObject;
    @Autowired
    ResponseObject<BillFoodDTO> billFoodDTOResponseObject;
    @Autowired
    ResponseObject<PromotionDTO> promotionDTOResponseObject;
    @Autowired
    ResponseObject<List<PromotionDTO>> promotionListResponseObject;
    @Autowired
    ResponseObject<List<BillFoodDTO>> billFoodListResponseObject;
    @Autowired
    ResponseObject<BillTicketDTO> billTicketDTOResponseObject;
    @Autowired
    ResponseObject<List<BillTicketDTO>> billTicketListResponseObject;
    @Autowired
    ResponseObject<TicketDTO> ticketDTOResponseObject;
    @Autowired
    ResponseObject<List<TicketDTO>> ticketListResponseObject;
    @Autowired
    ResponseObject<List<Map<String, Object>>> mapResponseObject;


    @Autowired
    private RepoServiceImpl repoService;


    @Override
    public ResponseObject<CinemaDTO> addCinema(AddCinemaRequest request) {
        try {
            var newCinema = cinemaConverter.addCinemaFromRequest(request);
            cinemaRepo.save(newCinema);
            CinemaDTO cinemaDTO = cinemaConverter.entityToDTO(newCinema);
            return cinemaDTOResponseObject.responseSuccess("Cinema added successfully", cinemaDTO);
        } catch (Exception e) {
            return cinemaDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to add cinema", null);
        }
    }

    @Override
    public ResponseObject<CinemaDTO> updateCinema(UpdateCinemaRequest request) {
        try {
            var optionalCinema = cinemaRepo.findById(request.getId());
            if (optionalCinema.isPresent()) {
                Cinema existingCinema = optionalCinema.get();
                existingCinema = cinemaConverter.updateCinemaFromRequest(request, existingCinema);
                Cinema updatedCinema = cinemaRepo.save(existingCinema);
                CinemaDTO cinemaDTO = cinemaConverter.entityToDTO(updatedCinema);
                return cinemaDTOResponseObject.responseSuccess("Cinema updated successfully", cinemaDTO);
            } else {
                return cinemaDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Cinema not found", null);
            }
        } catch (Exception e) {
            return cinemaDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to update cinema", null);
        }

    }

    @Override
    public ResponseObject<CinemaDTO> deleteCinema(int id) {
        try {
            var optionalCinema = cinemaRepo.findById(id);
            if (optionalCinema.isPresent()) {
                repoService.deleteRelativeColumnofCinema(id);
                cinemaRepo.deleteById(id);
                CinemaDTO cinemaDTO = cinemaConverter.entityToDTO(optionalCinema.get());
                return cinemaDTOResponseObject.responseSuccess( "Cinema deleted successfully", cinemaDTO);
            } else {
                return cinemaDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Cinema not found", null);
            }
        } catch (Exception e) {
            return cinemaDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to delete cinema", null);
        }
    }

    @Override
    public ResponseObject<RoomDTO> addRoom(AddRoomRequest request) {
        try {
            var room = roomConverter.addRoomFromRequest(request);
            room = roomRepo.save(room);
            RoomDTO roomDTO = roomConverter.entityToDTO(room);
            return roomDTOResponseObject.responseSuccess("Room added successfully", roomDTO);
        } catch (Exception e) {
            return roomDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to add room", null);
        }
    }

    @Override
    public ResponseObject<RoomDTO> updateRoom(UpdateRoomRequest request) {
        try {
            var optionalRoom = roomRepo.findById(request.getId());
            if (optionalRoom.isPresent()) {
                var existingRoom = optionalRoom.get();
                existingRoom = roomConverter.updateRoomFromRequest(request, existingRoom);
                existingRoom = roomRepo.save(existingRoom);
                RoomDTO roomDTO = roomConverter.entityToDTO(existingRoom);
                return roomDTOResponseObject.responseSuccess("Room updated successfully", roomDTO);
            } else {
                return roomDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND,"Room not found", null);
            }
        } catch (Exception e) {
            return roomDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Failed to update room", null);
        }
    }

    @Override
    public ResponseObject<RoomDTO> deleteRoom(int id) {
        try {
            var optionalRoom = roomRepo.findById(id);
            if (optionalRoom.isPresent()) {
                repoService.deleteRelativeColumnOfRoom(id);
                roomRepo.deleteById(id);
                return roomDTOResponseObject.responseSuccess("Room deleted successfully", null);
            } else {
                return roomDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND,"Room not found", null);
            }
        } catch (Exception e) {
            return roomDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Failed to delete room", null);
        }
    }
    @Override
    public ResponseObject<SeatDTO> addSeat(AddSeatRequest request) {
        try {
            var seat = seatConverter.addSeatFromRequest(request);
            seat = seatRepo.save(seat);
            SeatDTO seatDTO = seatConverter.entityToDTO(seat);
            return seatDTOResponseObject.responseSuccess("Seat added successfully", seatDTO);
        } catch (Exception e) {
            return seatDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Failed to add seat", null);
        }
    }

    @Override
    public ResponseObject<SeatDTO> updateSeat(UpdateSeatRequest request) {
        try {
            var optionalSeat = seatRepo.findById(request.getId());
            if (optionalSeat.isPresent()) {
                var existingSeat = optionalSeat.get();
                existingSeat = seatConverter.updateSeatFromRequest(request, existingSeat);
                existingSeat = seatRepo.save(existingSeat);
                SeatDTO seatDTO = seatConverter.entityToDTO(existingSeat);
                return seatDTOResponseObject.responseSuccess("Seat updated successfully", seatDTO);
            } else {
                return seatDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND,"Seat not found", null);
            }
        } catch (Exception e) {
            return seatDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Failed to update seat", null);
        }
    }

    @Override
    public ResponseObject<SeatDTO> deleteSeat(int id) {
        try {
            var optionalSeat = seatRepo.findById(id);
            if (optionalSeat.isPresent()) {
                repoService.deleteRelativeColumnOfSeat(id);
                seatRepo.deleteById(id);
                return seatDTOResponseObject.responseSuccess("Seat deleted successfully", null);
            } else {
                return seatDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND,"Seat not found", null);
            }
        } catch (Exception e) {
            return seatDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Failed to delete seat", null);
        }
    }


    @Override
    public ResponseObject<FoodDTO> addFood(AddFoodRequest request) {
        try {
            var food = foodConverter.addFoodFromRequest(request);
            food = foodRepo.save(food);
            FoodDTO foodDTO = foodConverter.entityToDTO(food);
            return foodDTOResponseObject.responseSuccess("Food added successfully", foodDTO);
        } catch (Exception e) {
            return foodDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Failed to add food", null);
        }
    }

    @Override
    public ResponseObject<FoodDTO> updateFood(UpdateFoodRequest request) {
        try {
            var optionalFood = foodRepo.findById(request.getId());
            if (optionalFood.isPresent()) {
                var existingFood = optionalFood.get();
                existingFood = foodConverter.updateFoodFromRequest(request, existingFood);
                existingFood = foodRepo.save(existingFood);
                FoodDTO foodDTO = foodConverter.entityToDTO(existingFood);
                return foodDTOResponseObject.responseSuccess("Food updated successfully", foodDTO);
            } else {
                return foodDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND,"Food not found", null);
            }
        } catch (Exception e) {
            return foodDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Failed to update food", null);
        }
    }

    @Override
    public ResponseObject<FoodDTO> deleteFood(int id) {
        try {
            var optionalFood = foodRepo.findById(id);
            if (optionalFood.isPresent()) {
                repoService.deleteRelativeColumnOfFood(id);
                foodRepo.deleteById(id);
                return foodDTOResponseObject.responseSuccess("Food deleted successfully", null);
            } else {
                return foodDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND,"Food not found", null);
            }
        } catch (Exception e) {
            return foodDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Failed to delete food", null);
        }
    }

    @Override
    public ResponseObject<MovieDTO> addMovie(AddMovieRequest request) {
        try {
            var newMovie = movieConverter.addMovieFromRequest(request);
            movieRepo.save(newMovie);
            MovieDTO movieDTO = movieConverter.entityToDTO(newMovie);
            return movieDTOResponseObject.responseSuccess("Movie added successfully", movieDTO);
        } catch (Exception e) {
            return movieDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to add movie + " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseObject<MovieDTO> updateMovie(UpdateMovieRequest request) {
        try {
            var optionalMovie = movieRepo.findById(request.getId());
            if (optionalMovie.isPresent()) {
                var existingMovie = optionalMovie.get();
                existingMovie = movieConverter.updateMovieFromRequest(request, existingMovie);
                var updatedMovie = movieRepo.save(existingMovie);
                MovieDTO movieDTO = movieConverter.entityToDTO(updatedMovie);
                return movieDTOResponseObject.responseSuccess("Movie updated successfully", movieDTO);
            } else {
                return movieDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Movie not found", null);
            }
        } catch (Exception e) {
            return movieDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to update movie", null);
        }
    }

    @Override
    public ResponseObject<MovieDTO> deleteMovie(int id) {
        try {
            var optionalMovie = movieRepo.findById(id);
            if (optionalMovie.isPresent()) {
                repoService.deleteRelativeColumnOfMovie(id);
                movieRepo.deleteById(id);
                MovieDTO movieDTO = movieConverter.entityToDTO(optionalMovie.get());
                return movieDTOResponseObject.responseSuccess("Movie deleted successfully", movieDTO);
            } else {
                return movieDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Movie not found", null);
            }
        } catch (Exception e) {
            return movieDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to delete movie", null);
        }
    }

    @Override
    public ResponseObject<ScheduleDTO> addSchedule(AddScheduleRequest request) {
        if (isScheduleOverlap(request.getRoomId(), request.getStartAt(), request.getEndAt())) {
            return scheduleDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Schedule overlaps with existing schedules", null);
        }
        try {
            Schedule newSchedule = scheduleConverter.addScheduleFromRequest(request);
            scheduleRepo.save(newSchedule);
            ScheduleDTO scheduleDTO = scheduleConverter.entityToDTO(newSchedule);
            return scheduleDTOResponseObject.responseSuccess("Schedule added successfully", scheduleDTO);
        } catch (Exception e) {
            return scheduleDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to add schedule", null);
        }
    }

    private boolean isScheduleOverlap(int roomId, LocalDateTime startAt, LocalDateTime endAt) {
        List<Schedule> existingSchedules = scheduleRepo.findByRoomIdAndTimeRange(roomId, startAt, endAt);
        return !existingSchedules.isEmpty();
    }

    @Override
    public ResponseObject<ScheduleDTO> updateSchedule(UpdateScheduleRequest request) {
        try {
            var optionalSchedule = scheduleRepo.findById(request.getId());
            if (optionalSchedule.isPresent()) {
                var existingSchedule = optionalSchedule.get();
                existingSchedule = scheduleConverter.updateScheduleFromRequest(request, existingSchedule);
                var updatedSchedule = scheduleRepo.save(existingSchedule);
                var scheduleDTO = scheduleConverter.entityToDTO(updatedSchedule);
                return scheduleDTOResponseObject.responseSuccess("Schedule updated successfully", scheduleDTO);
            } else {
                return scheduleDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Schedule not found", null);
            }
        } catch (Exception e) {
            return scheduleDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to update schedule", null);
        }
    }

    @Override
    public ResponseObject<ScheduleDTO> deleteSchedule(int id) {
        try {
            var optionalSchedule = scheduleRepo.findById(id);
            if (optionalSchedule.isPresent()) {
                repoService.deleteRelativeColumnOfSchedule(id);
                scheduleRepo.deleteById(id);
                var scheduleDTO = scheduleConverter.entityToDTO(optionalSchedule.get());
                return scheduleDTOResponseObject.responseSuccess("Schedule deleted successfully", scheduleDTO);
            } else {
                return scheduleDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Schedule not found", null);
            }
        } catch (Exception e) {
            return scheduleDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to delete schedule", null);
        }
    }

    @Override
    public ResponseObject<PromotionDTO> addPromotion(AddPromotionRequest request) {
        try {
            var promotion = promotionConverter.addPromotionFromRequest(request);
            promotionRepo.save(promotion);
            PromotionDTO promotionDTO = promotionConverter.entityToDTO(promotion);
            return promotionDTOResponseObject.responseSuccess("Promotion added successfully", promotionDTO);
        } catch (Exception e) {
            return promotionDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to add promotion", null);
        }
    }

    @Override
    public ResponseObject<PromotionDTO> updatePromotion(UpdatePromotionRequest request) {
        try {
            var optionalPromotion = promotionRepo.findById(request.getId());
            if (optionalPromotion.isPresent()) {
                var existingPromotion = optionalPromotion.get();
                existingPromotion = promotionConverter.updatePromotionFromRequest(request, existingPromotion);
                var updatedPromotion = promotionRepo.save(existingPromotion);
                var promotionDTO = promotionConverter.entityToDTO(updatedPromotion);
                return promotionDTOResponseObject.responseSuccess("Promotion updated successfully", promotionDTO);
            } else {
                return promotionDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Promotion not found", null);
            }
        } catch (Exception e) {
            return promotionDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to update promotion", null);
        }
    }

    @Override
    public ResponseObject<PromotionDTO> deletePromotion(String name) {
        try {
            var existingPromotion = promotionRepo.findByName(name);
            if (existingPromotion != null) {
                repoService.deleteRelativeColumnOfPromotion(existingPromotion.getId());
                promotionRepo.deleteById(existingPromotion.getId());
                var promotionDTO = promotionConverter.entityToDTO(existingPromotion);
                return promotionDTOResponseObject.responseSuccess("Promotion deleted successfully", promotionDTO);
            } else {
                return promotionDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Promotion not found", null);
            }
        } catch (Exception e) {
            return promotionDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to delete promotion", null);
        }
    }

    @Override
    public ResponseObject<BillFoodDTO> addBillFood(AddBillFoodRequest request) {
        try {
            var billFood = billFoodConverter.addBillFoodFromRequest(request);
            billFoodRepo.save(billFood);
            BillFoodDTO billFoodDTO = billFoodConverter.entityToDTO(billFood);
            return billFoodDTOResponseObject.responseSuccess("Bill food added successfully", billFoodDTO);
        } catch (Exception e) {
            return billFoodDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to add bill food", null);
        }
    }

    @Override
    public ResponseObject<BillFoodDTO> updateBillFood(UpdateBillFoodRequest request) {
        try {
            var optionalBillFood = billFoodRepo.findById(request.getId());
            if (optionalBillFood.isPresent()) {
                var existingBillFood = optionalBillFood.get();
                existingBillFood = billFoodConverter.updateBillFoodFromRequest(request, existingBillFood);
                var updatedBillFood = billFoodRepo.save(existingBillFood);
                var billFoodDTO = billFoodConverter.entityToDTO(updatedBillFood);
                return billFoodDTOResponseObject.responseSuccess("Bill food updated successfully", billFoodDTO);
            } else {
                return billFoodDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Bill food not found", null);
            }
        } catch (Exception e) {
            return billFoodDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to update bill food", null);
        }
    }

    @Override
    public ResponseObject<BillFoodDTO> deleteBillFood(int id) {
        try {
            var optionalBillFood = billFoodRepo.findById(id);
            if (optionalBillFood.isPresent()) {
                billFoodRepo.deleteById(id);
                return billFoodDTOResponseObject.responseSuccess("Bill food deleted successfully", null);
            } else {
                return billFoodDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Bill food not found", null);
            }
        } catch (Exception e) {
            return billFoodDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to delete bill food", null);
        }
    }

    @Override
    public ResponseObject<BillTicketDTO> addBillTicket(AddBillTicketRequest request) {
        try {
            var billTicket = billTicketConverter.addBillTicketFromRequest(request);
            billTicKetRepo.save(billTicket);
            BillTicketDTO billTicketDTO = billTicketConverter.entityToDTO(billTicket);
            return billTicketDTOResponseObject.responseSuccess("Bill ticket added successfully", billTicketDTO);
        } catch (Exception e) {
            return billTicketDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to add bill ticket", null);
        }
    }

    @Override
    public ResponseObject<BillTicketDTO> updateBillTicket(UpdateBillTicketRequest request) {
        try {
            var optionalBillTicket = billTicKetRepo.findById(request.getId());
            if (optionalBillTicket.isPresent()) {
                var existingBillTicket = optionalBillTicket.get();
                existingBillTicket = billTicketConverter.updateBillTicketFromRequest(request, existingBillTicket);
                var updatedBillTicket = billTicKetRepo.save(existingBillTicket);
                var billTicketDTO = billTicketConverter.entityToDTO(updatedBillTicket);
                return billTicketDTOResponseObject.responseSuccess("Bill ticket updated successfully", billTicketDTO);
            } else {
                return billTicketDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Bill ticket not found", null);
            }
        } catch (Exception e) {
            return billTicketDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to update bill ticket", null);
        }
    }

    @Override
    public ResponseObject<BillTicketDTO> deleteBillTicket(int id) {
        try {
            var optionalBillTicket = billTicKetRepo.findById(id);
            if (optionalBillTicket.isPresent()) {
                billTicKetRepo.deleteById(id);
                return billTicketDTOResponseObject.responseSuccess("Bill ticket deleted successfully", null);
            } else {
                return billTicketDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Bill ticket not found", null);
            }
        } catch (Exception e) {
            return billTicketDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to delete bill ticket", null);
        }
    }

    @Override
    public ResponseObject<TicketDTO> addTicket(AddTicketRequest request) {
        try {
            var ticket = ticketConverter.addTicketFromRequest(request);
            ticketRepo.save(ticket);
            TicketDTO ticketDTO = ticketConverter.entityToDTO(ticket);
            return ticketDTOResponseObject.responseSuccess("Ticket added successfully", ticketDTO);
        } catch (Exception e) {
            return ticketDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to add ticket", null);
        }
    }

    @Override
    public ResponseObject<TicketDTO> updateTicket(UpdateTicketRequest request) {
        try {
            var optionalTicket = ticketRepo.findById(request.getId());
            if (optionalTicket.isPresent()) {
                var existingTicket = optionalTicket.get();
                existingTicket = ticketConverter.updateTicketFromRequest(request, existingTicket);
                var updatedTicket = ticketRepo.save(existingTicket);
                var ticketDTO = ticketConverter.entityToDTO(updatedTicket);
                return ticketDTOResponseObject.responseSuccess("Ticket updated successfully", ticketDTO);
            } else {
                return ticketDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Ticket not found", null);
            }
        } catch (Exception e) {
            return ticketDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to update ticket", null);
        }
    }

    @Override
    public ResponseObject<TicketDTO> deleteTicket(int ticketId) {
        try {
            var optionalTicket = ticketRepo.findById(ticketId);
            if (optionalTicket.isPresent()) {
                repoService.deleteRelativeColumnOfTicket(ticketId);
                ticketRepo.deleteById(ticketId);
                return ticketDTOResponseObject.responseSuccess("Ticket deleted successfully", null);
            } else {
                return ticketDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Ticket not found", null);
            }
        } catch (Exception e) {
            return ticketDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to delete ticket", null);
        }
    }

    @Override
    public ResponseObject<List<Map<String, Object>>> getCinemaRevenueStatistics(LocalDateTime startTime, LocalDateTime endTime) {
        try {
            List<Map<String, Object>> cinemaRevenueList = new ArrayList<>();

            List<Cinema> cinemas = cinemaRepo.findAll();

            for (Cinema cinema : cinemas) {
                Double cinemaRevenue = calculateCinemaRevenue(cinema.getId(), startTime, endTime);

                Map<String, Object> cinemaRevenueMap = new HashMap<>();
                cinemaRevenueMap.put("cinemaName", cinema.getNameOfCinema());
                cinemaRevenueMap.put("revenue", cinemaRevenue);

                cinemaRevenueList.add(cinemaRevenueMap);
            }

            return mapResponseObject.responseSuccess("Cinema revenue statistics retrieved successfully", cinemaRevenueList);
        } catch (Exception e) {
            return mapResponseObject.responseError(HttpURLConnection.HTTP_INTERNAL_ERROR, "Failed to retrieve cinema revenue statistics", null);
        }
    }

    private Double calculateCinemaRevenue(Integer cinemaId, LocalDateTime startTime, LocalDateTime endTime) {
        Double totalRevenue = cinemaRepo.getCinemaRevenueBetween(cinemaId, startTime, endTime);
        return totalRevenue != null ? totalRevenue : 0.0;
    }


    @Override
    public ResponseObject<List<FoodDTO>> getTopSellingFoodsLast7Days(LocalDateTime startTime, LocalDateTime endTime) {
        try {
            List<Bill> bills = billRepo.findByCreateTimeBetween(startTime, endTime);

            Map<Food, Integer> foodSalesMap = new HashMap<>();

            for (Bill bill : bills) {
                List<BillFood> billFoods = billFoodRepo.findByBillId(bill.getId());
                for (BillFood billFood : billFoods) {
                    Food food = billFood.getFoodfromBillFood();
                    foodSalesMap.put(food, foodSalesMap.getOrDefault(food, 0) + billFood.getQuantity());
                }
            }

            List<Map.Entry<Food, Integer>> sortedFoodSales = foodSalesMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .toList();

            List<FoodDTO> topSellingFoods = new ArrayList<>();
            int count = 0;
            for (Map.Entry<Food, Integer> entry : sortedFoodSales) {
                if (count >= 10) {
                    break;
                }
                Food food = entry.getKey();
                Integer quantity = entry.getValue();
                topSellingFoods.add(foodConverter.entityToDTO(food));
                count++;
            }
            return foodListResponseObject.responseSuccess("Top selling foods in the last 7 days retrieved successfully", topSellingFoods);
        } catch (Exception e) {
            return foodListResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Failed to retrieve top selling foods", null);
        }
    }
}
