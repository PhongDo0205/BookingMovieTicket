package com.example.BookingMovieTickets.Service.Impl;

import com.example.BookingMovieTickets.Entity.*;
import com.example.BookingMovieTickets.Payload.Converter.*;
import com.example.BookingMovieTickets.Payload.DTO.*;
import com.example.BookingMovieTickets.Payload.Request.User.UpdateProfileRequest;
import com.example.BookingMovieTickets.Payload.Respond.ResponseObject;
import com.example.BookingMovieTickets.Repository.*;
import com.example.BookingMovieTickets.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ConfirmEmailRepo confirmEmailRepo;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private CinemaRepo cinemaRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private SeatRepo seatRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private FoodRepo foodRepo;
    @Autowired
    private RankCustomerRepo rankCustomerRepo;
    @Autowired
    private PromotionRepo promotionRepo;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private BillFoodRepo billFoodRepo;
    @Autowired
    private BillTicKetRepo billTicKetRepo;

    @Autowired
    private UserConverter usersConverter;
    @Autowired
    private ConfirmEmailConverter confirmEmailConverter;
    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private CinemaConverter cinemaConverter;
    @Autowired
    private RoomConverter roomConverter;
    @Autowired
    private ScheduleConverter scheduleConverter;
    @Autowired
    private FoodConverter foodConverter;
    @Autowired
    private BillConverter billConverter;
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
    private ResponseObject<List<UserDTO>> userListResponseObject;
    @Autowired
    private ResponseObject<ConfirmEmailDTO> confirmEmailDTOResponseObject;
    @Autowired
    private ResponseObject<List<MovieDTO>> movieListResponseObject;
    @Autowired
    ResponseObject<List<CinemaDTO>> cinemaListResponseObject;
    @Autowired
    ResponseObject<List<RoomDTO>> roomListResponseObject;
    @Autowired
    ResponseObject<List<ScheduleDTO>> scheduleListResponseObject;
    @Autowired
    ResponseObject<List<FoodDTO>> foodListResponseObject;
    @Autowired
    private ResponseObject<String> stringResponseObject;
    @Autowired
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
    private JavaMailSender javaMailSender;

    @Autowired
    private VNPayServiceImpl vnPayService;


    @Override
    public ResponseObject<ConfirmEmailDTO> confirmEmail(String username, String confirmCode) {
        var user = userRepo.findAll().stream().filter(x-> Objects.equals(x.getName(), username)).findFirst().orElseThrow(null);
        if (user == null) {
            return confirmEmailDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "User not found", null);
        }
        if (confirmCode == null) {
            return confirmEmailDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Invalid confirmCode", null);
        }
        ConfirmEmail confirmEmail = confirmEmailRepo.findByUserId(user.getId());
        if (confirmEmail != null) {
            if (confirmEmail.getRequiredDateTime().isAfter(LocalDateTime.now().minusMinutes(15))) {
                user.setActive(true);
                userRepo.save(user);

                confirmEmail.setConfirm(true);
                confirmEmail.setExquiredDateTime(LocalDateTime.now());
                confirmEmailRepo.save(confirmEmail);

                ConfirmEmailDTO confirmEmailDTO = confirmEmailConverter.entityToDTO(confirmEmail);
                return confirmEmailDTOResponseObject.responseSuccess("Email confirmation successful", confirmEmailDTO);
            } else {
                return confirmEmailDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Email confirmation code has expired", null);
            }
        }else{
            return confirmEmailDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Email confirmation not found", null);
        }
    }

    @Override
    public ResponseObject<UserDTO> getUserById(int id) {
        var user = userRepo.findAll().stream().filter(x-> Objects.equals(x.getId(), id)).findFirst().orElseThrow(null);
        if (user == null) {
            return userDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "User not found", null);
        }else{
            return userDTOResponseObject.responseSuccess("User information by id: ", usersConverter.entityToDTO(user));
        }
    }

    @Override
    public ResponseObject<List<UserDTO>> getAllUser(Pageable pageable) {
        var userList = userRepo.findAll(pageable).stream().toList();
        return userListResponseObject.responseSuccess("User Information: ", usersConverter.entityToDTOList(userList));
    }

    @Override
    public ResponseObject<List<MovieDTO>> getTopMovie(Pageable pageable) {
        var movieList = movieRepo.findTopMoviesByTicketSales(pageable).stream().toList();
        return movieListResponseObject.responseSuccess("Top movie: ", movieConverter.entityToDTOList(movieList));
    }

    @Override
    public ResponseObject<List<MovieDTO>> getMoviesByCinemaRoomAndSeatStatus(int cinemaId, int roomId, int seatStatusId) {
        var movieList = movieRepo.findMoviesByCinemaAndRoomAndSeatStatus(cinemaId, roomId, seatStatusId);
        return movieListResponseObject.responseSuccess("Movie list: ", movieConverter.entityToDTOList(movieList));
    }

    @Override
    public ResponseObject<List<MovieDTO>> getAllMovie(Pageable pageable) {
        var movieList = movieRepo.findAll(pageable).stream().toList();
        return movieListResponseObject.responseSuccess("Movie list: ", movieConverter.entityToDTOList(movieList));
    }

    @Override
    public ResponseObject<List<CinemaDTO>> getAllCinemaHaveMovie(int movieId) {
        var cinemaList = cinemaRepo.findCinemasByMovie(movieId);
        return cinemaListResponseObject.responseSuccess("Cinema List: ", cinemaConverter.entityToDTOList(cinemaList));
    }

    @Override
    public ResponseObject<List<RoomDTO>> getAllRoomInCinema(int cinemaId, int movieId) {
        var roomList = roomRepo.findRoomsByCinemaAndMovie(cinemaId, movieId);
        return roomListResponseObject.responseSuccess("Room List: ", roomConverter.entityToDTOList(roomList));
    }

    @Override
    public ResponseObject<List<ScheduleDTO>> getAllScheduleByRoom(int roomId) {
        var scheduleList = scheduleRepo.findAll().stream().filter(x->x.getRoomId() == roomId).toList();
        return scheduleListResponseObject.responseSuccess("Schedule: ", scheduleConverter.entityToDTOList(scheduleList));
    }

    @Override
    public ResponseObject<List<TicketDTO>> getAllTicketBySchedule(int scheduleId) {
        var ticketList = ticketRepo.findAll().stream().filter(x->x.getScheduleId() == scheduleId).toList();
        List<Ticket> availableTicketList = new ArrayList<>();
        for(Ticket ticket:ticketList){
            var billTicket = billTicKetRepo.findByTicketId(ticket.getId());
            if(billTicket == null){
                availableTicketList.add(ticket);
            }
        }
        return ticketListResponseObject.responseSuccess("Available Ticket list by schedule: ", ticketConverter.entityToDTOList(availableTicketList));
    }


    @Override
    public ResponseObject<List<FoodDTO>> getAllFood(Pageable pageable) {
        var foodList = foodRepo.findAll(pageable).stream().toList();
        return foodListResponseObject.responseSuccess("Food List: ", foodConverter.entityToDTOList(foodList));
    }

    @Override
    public ResponseObject<BillDTO> createBill(String username, int scheduleId) {
        try {
            var schedule = scheduleRepo.findById(scheduleId).orElseThrow(null);
            if (schedule == null) {
                return billDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Schedule not found!", null);
            }
            double totalAmount = 0.0;
            var customer = userRepo.findByName(username);
            if (customer.isEmpty() || !customer.get().isActive()) {
                return billDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Schedule not found or not active!", null);
            }
            var rankCustomer = rankCustomerRepo.findById(customer.get().getRankCustomerId()).orElseThrow(null);
            if (rankCustomer == null) {
                return billDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Rank Customer not found!", null);
            }
            var promotion = promotionRepo.findAll().stream().filter(x -> x.getRankCustomerId() == rankCustomer.getId()).findFirst().orElseThrow(null);
            Bill bill = new Bill();
            if (promotion != null) {
                bill.setTotalMoney(totalAmount);
                bill.setTradingCode("Bill");
                bill.setCreateTime(LocalDateTime.now());
                bill.setCustomerId(customer.get().getId());
                bill.setName("Bill for user " + customer.get().getName());
                bill.setUpdateTime(LocalDateTime.now());
                bill.setPromotionId(promotion.getId());
                bill.setActive(true);
                bill.setBillStatusId(4);
                billRepo.save(bill);
            }
            return billDTOResponseObject.responseSuccess("Create Bill Successfully!", billConverter.entityToDTO(bill));
        }catch (Exception e){
            return billDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Exception: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseObject<String> processPayment(int billId, String paymentMethod, HttpServletRequest request) throws IOException {
        var bill = billRepo.findById(billId).orElseThrow(null);
        if(bill == null){
            return stringResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Bill not found!", null);
        }

        var customer = userRepo.findById(bill.getCustomerId());
        if(customer == null){
            return stringResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "CustomerId not found!", null);
        }
        if (bill.getBillStatusId() == 3 || bill.getBillStatusId() == 2) {
            return stringResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Bill is canceled or paid!", null);
        }
        double totalAmount = getTotalAmount(billId, bill.getCustomerId());
        bill.setTotalMoney(totalAmount);
        billRepo.save(bill);
        boolean paymentSuccessful = processPaymentLogic(bill, paymentMethod, request);
        if (paymentSuccessful) {
            bill.setBillStatusId(2);
            billRepo.save(bill);
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setTo(customer.getEmail());
                helper.setSubject("Payment processed successfully!");
                helper.setText("Total money: " + totalAmount);

                javaMailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send email!");
            }
            return stringResponseObject.responseSuccess("Payment processed successfully!", "Payment successful");
        } else {
            bill.setBillStatusId(3);
            billRepo.save(bill);
            return stringResponseObject.responseError(HttpURLConnection.HTTP_INTERNAL_ERROR, "Payment processing failed!", null);
        }
    }

    private boolean processPaymentLogic(Bill bill, String paymentMethod, HttpServletRequest request) throws IOException {
        int total = (int) (bill.getTotalMoney()*10000);
        String orderInfo = bill.getName();
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setOrderInfo(orderInfo);
        orderRequestDTO.setAmount((long) total);
        Map<String, Object> orderResult = vnPayService.createOrder(request, orderRequestDTO);

        if (orderResult != null && orderResult.containsKey("redirect_url")) {
            String redirectUrl = orderResult.get("redirect_url").toString();
            HttpServletResponse response = (HttpServletResponse) request.getAttribute("javax.servlet.error.response");
            response.sendRedirect(redirectUrl);
            return true;
        } else {
            return false;
        }
    }


    private double getTotalAmount(int billId, int customerId){
        double totalAmount = 0.0;
        var bill = billRepo.findById(billId).orElseThrow(null);
        if(bill!=null){
            List<BillFood> billFoodList = billFoodRepo.findAll().stream().filter(x->x.getBillId()==billId).toList();
            for(BillFood billFood:billFoodList){
                var food = foodRepo.findById(billFood.getFoodId());
                if(food.isPresent()){
                    double amount = (food.get().getPrice()*billFood.getQuantity());
                    totalAmount += amount;
                }
            }
            List<BillTicket> billTicketList = billTicKetRepo.findAll().stream().filter(x->x.getBillId() == billId).toList();
            for(BillTicket billTicket:billTicketList){
                var ticket = ticketRepo.findById(billTicket.getTicketId());
                if(ticket.isPresent()){
                    double amount = ticket.get().getPriceTicket()*billTicket.getQuantity();
                    totalAmount += amount;
                }
            }
            var rankCustomer = rankCustomerRepo.findById(customerId).orElseThrow(null);
            var promotion = promotionRepo.findAll().stream().filter(x -> x.getRankCustomerId() == rankCustomer.getId()).findFirst().orElseThrow(null);
            if (promotion != null) {
                if (promotion.getQuantity() >= 0 &&
                        promotion.getStartTime().isBefore(LocalDateTime.now()) &&
                        promotion.getEndTime().isAfter(LocalDateTime.now()) &&
                        promotion.isActive()) {
                    double discountAmount = totalAmount * promotion.getPercent() / 100;
                    totalAmount -= discountAmount;
                    promotion.setQuantity(promotion.getQuantity() - 1);
                    promotionRepo.save(promotion);
                }
            }
        }
        return totalAmount;
    }

    @Override
    public ResponseObject<List<PromotionDTO>> getAllPromotion(Pageable pageable) {
        var promotionList = promotionRepo.findAll(pageable).stream().toList();
        return promotionListResponseObject.responseSuccess("All Promotions", promotionConverter.entityToDTOList(promotionList));
    }

    @Override
    public ResponseObject<PromotionDTO> getPromotionByName(String promotionName) {
        var promotion = promotionRepo.findByName(promotionName);
        if (promotion == null) {
            return promotionDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Promotion not found!", null);
        }
        return promotionDTOResponseObject.responseSuccess("Promotion found", promotionConverter.entityToDTO(promotion));
    }


    @Override
    public ResponseObject<BillFoodDTO> getBillFoodById(int billFoodId) {
        var billFood = billFoodRepo.findById(billFoodId).orElse(null);
        if (billFood == null) {
            return billFoodDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Bill Food not found!", null);
        }
        return billFoodDTOResponseObject.responseSuccess("Bill Food found", billFoodConverter.entityToDTO(billFood));
    }

    @Override
    public ResponseObject<List<BillFoodDTO>> getAllBillFood(Pageable pageable) {
        var billFoodList = billFoodRepo.findAll(pageable).stream().toList();
        return billFoodListResponseObject.responseSuccess("All Bill Foods", billFoodConverter.entityToDTOList(billFoodList));
    }

    @Override
    public ResponseObject<BillTicketDTO> getBillTicketById(int billTicketId) {
        var billTicket = billTicKetRepo.findById(billTicketId).orElse(null);
        if (billTicket == null) {
            return billTicketDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Bill Ticket not found!", null);
        }
        return billTicketDTOResponseObject.responseSuccess("Bill Ticket found", billTicketConverter.entityToDTO(billTicket));
    }

    @Override
    public ResponseObject<List<BillTicketDTO>> getAllBillTicket(Pageable pageable) {
        var billTicketList = billTicKetRepo.findAll(pageable).stream().toList();
        return billTicketListResponseObject.responseSuccess("All Bill Tickets", billTicketConverter.entityToDTOList(billTicketList));
    }

    @Override
    public ResponseObject<UserDTO> updateProfile(String username, UpdateProfileRequest request) {
        var existingUser = userRepo.findByName(username);
        if(existingUser.isPresent()){
            var userUpdate = usersConverter.updateProfile(request, existingUser.get());
            userRepo.save(userUpdate);
            return userDTOResponseObject.responseSuccess("Update Successfully!", usersConverter.entityToDTO(userUpdate));
        }else{
            return userDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "User not found!", null);
        }
    }

    @Override
    public ResponseObject<List<TicketDTO>> getAllTicket(Pageable pageable) {
        var ticketList = ticketRepo.findAll(pageable).stream().toList();
        return ticketListResponseObject.responseSuccess("All Tickets", ticketConverter.entityToDTOList(ticketList));
    }

    @Override
    public ResponseObject<TicketDTO> getTicketById(int ticketId) {
        var ticket = ticketRepo.findById(ticketId).orElse(null);
        if (ticket == null) {
            return ticketDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Ticket not found!", null);
        }
        return ticketDTOResponseObject.responseSuccess("Ticket found", ticketConverter.entityToDTO(ticket));
    }
}
