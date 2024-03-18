package com.example.BookingMovieTickets.Service;

public interface RepoService {
    void deleteRelativeColumnofCinema(int cinemaId);

    void deleteRelativeColumnOfRoom(int roomId);

    void deleteRelativeColumnOfSchedule(int scheduleId);

    void deleteRelativeColumnOfSeat(int seatId);

    void deleteRelativeColumnOfTicket(int ticketId);

    void deleteRelativeColumnOfFood(int foodId);

    void deleteRelativeColumnOfMovie(int movieId);

    void deleteRelativeColumnOfPromotion(int promotionId);

    void deleteRelativeColumnOfBill(int BillId);
}
