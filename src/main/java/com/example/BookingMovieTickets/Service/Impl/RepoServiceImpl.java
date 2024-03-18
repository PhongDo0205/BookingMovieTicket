package com.example.BookingMovieTickets.Service.Impl;

import com.example.BookingMovieTickets.Entity.*;
import com.example.BookingMovieTickets.Repository.*;
import com.example.BookingMovieTickets.Service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoServiceImpl implements RepoService {
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
    private BillRepo billRepo;

    @Override
    public void deleteRelativeColumnofCinema(int cinemaId){
        var roomList = roomRepo.findAll().stream().filter(x->x.getCinemaId() == cinemaId).toList();
        for(Room room:roomList){
            deleteRelativeColumnOfRoom(room.getId());
        }
        roomRepo.deleteAll(roomList);
    }

    @Override
    public void deleteRelativeColumnOfRoom(int roomId){
        var seatList = seatRepo.findAll().stream().filter(x->x.getRoomId() == roomId).toList();
        for (Seat seat:seatList){
            deleteRelativeColumnOfSeat(seat.getId());
        }
        seatRepo.deleteAll(seatList);
        var scheduleList = scheduleRepo.findAll().stream().filter(x->x.getRoomId() == roomId).toList();
        for (Schedule schedule:scheduleList){
            deleteRelativeColumnOfSchedule(schedule.getId());
        }
        scheduleRepo.deleteAll(scheduleList);
    }

    @Override
    public void deleteRelativeColumnOfSchedule(int scheduleId){
        var ticketList = ticketRepo.findAll().stream().filter(x->x.getSeatId() == scheduleId).toList();
        for(Ticket ticket:ticketList){
            deleteRelativeColumnOfTicket(ticket.getId());
        }
        ticketRepo.deleteAll(ticketList);
    }

    @Override
    public void deleteRelativeColumnOfSeat(int seatId){
        var ticketList = ticketRepo.findAll().stream().filter(x->x.getSeatId() == seatId).toList();
        for(Ticket ticket:ticketList){
            deleteRelativeColumnOfTicket(ticket.getId());
        }
        ticketRepo.deleteAll(ticketList);
    }

    @Override
    public void deleteRelativeColumnOfTicket(int ticketId){
        var billTicketList = billTicKetRepo.findAll().stream().filter(x->x.getTicketId() == ticketId).toList();
        billTicKetRepo.deleteAll(billTicketList);
    }

//    public void deleteRelativeColumnOfBillTicket(int billTicketId){
//
//    }

    @Override
    public void deleteRelativeColumnOfFood(int foodId){
        var billFoodList = billFoodRepo.findAll().stream().filter(x->x.getFoodId() == foodId).toList();
        billFoodRepo.deleteAll(billFoodList);
    }

    @Override
    public void deleteRelativeColumnOfMovie(int movieId){
        var scheduleList = scheduleRepo.findAll().stream().filter(x->x.getMovieId() == movieId).toList();
        for(Schedule schedule:scheduleList){
            deleteRelativeColumnOfSchedule(schedule.getId());
        }
        scheduleRepo.deleteAll(scheduleList);
    }

    @Override
    public void deleteRelativeColumnOfPromotion(int promotionId) {
        var billList = billRepo.findAll().stream().filter(x->x.getPromotionId() == promotionId).toList();
        for(Bill bill:billList){
            deleteRelativeColumnOfBill(bill.getId());
        }
    }

    @Override
    public void deleteRelativeColumnOfBill(int billId) {
        var billFoodList = billFoodRepo.findAll().stream().filter(x->x.getBillId() == billId).toList();
        for(BillFood billFood:billFoodList){
            billFoodRepo.deleteById(billFood.getId());
        }
        var billTicketList = billTicKetRepo.findAll().stream().filter(x->x.getBillId() == billId).toList();
        for (BillTicket billTicket:billTicketList){
            billTicKetRepo.deleteById(billTicket.getId());
        }
    }

}
