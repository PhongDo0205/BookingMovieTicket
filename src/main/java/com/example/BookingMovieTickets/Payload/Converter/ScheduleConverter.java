package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Schedule;
import com.example.BookingMovieTickets.Payload.DTO.ScheduleDTO;
import com.example.BookingMovieTickets.Payload.Request.Schedule.AddScheduleRequest;
import com.example.BookingMovieTickets.Payload.Request.Schedule.UpdateScheduleRequest;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleConverter {
    public ScheduleDTO entityToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setPrice(schedule.getPrice());
        scheduleDTO.setStartAt(schedule.getStartAt());
        scheduleDTO.setEndAt(schedule.getEndAt());
        scheduleDTO.setCode(schedule.getCode());
        scheduleDTO.setMovieId(schedule.getMovieId());
        scheduleDTO.setName(schedule.getName());
        scheduleDTO.setRoomId(schedule.getRoomId());
        scheduleDTO.setIsActive(schedule.getIsActive());
        return scheduleDTO;
    }

    public List<ScheduleDTO> entityToDTOList(List<Schedule> scheduleList) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            assert false;
            scheduleDTOList.add(entityToDTO(schedule));
        }
        return scheduleDTOList;
    }

    public Schedule addScheduleFromRequest(AddScheduleRequest request) {
        Schedule schedule = new Schedule();
        schedule.setPrice(request.getPrice());
        schedule.setStartAt(request.getStartAt());
        schedule.setEndAt(request.getEndAt());
        schedule.setCode(request.getCode());
        schedule.setMovieId(request.getMovieId());
        schedule.setName(request.getName());
        schedule.setRoomId(request.getRoomId());
        schedule.setIsActive(request.getIsActive());
        return schedule;
    }

    public Schedule updateScheduleFromRequest(UpdateScheduleRequest request, Schedule existingSchedule) {
        if (request.getId() == 0) {
            return null;
        } else {
            existingSchedule.setId(request.getId());
        }

        if (request.getPrice() > 0) {
            existingSchedule.setPrice(request.getPrice());
        }

        if (request.getStartAt() != null) {
            existingSchedule.setStartAt(request.getStartAt());
        }

        if (request.getEndAt() != null) {
            existingSchedule.setEndAt(request.getEndAt());
        }

        if (request.getCode() != null) {
            existingSchedule.setCode(request.getCode());
        }

        if (request.getMovieId() > 0) {
            existingSchedule.setMovieId(request.getMovieId());
        }

        if (request.getName() != null) {
            existingSchedule.setName(request.getName());
        }

        if (request.getRoomId() > 0) {
            existingSchedule.setRoomId(request.getRoomId());
        }

        existingSchedule.setIsActive(request.getIsActive());

        return existingSchedule;
    }
}

