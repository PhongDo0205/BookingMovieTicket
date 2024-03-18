package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Room;
import com.example.BookingMovieTickets.Payload.DTO.RoomDTO;
import com.example.BookingMovieTickets.Payload.Request.Room.AddRoomRequest;
import com.example.BookingMovieTickets.Payload.Request.Room.UpdateRoomRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class RoomConverter {
    public RoomDTO entityToDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setType(room.getType());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setCinemaId(room.getCinemaId());
        roomDTO.setCode(room.getCode());
        roomDTO.setName(room.getName());
        roomDTO.setActive(room.isActive());
        return roomDTO;
    }

    public Room addRoomFromRequest(AddRoomRequest request) {
        Room room = new Room();
        room.setCapacity(request.getCapacity());
        room.setType(request.getType());
        room.setDescription(request.getDescription());
        room.setCinemaId(request.getCinemaId());
        room.setCode(request.getCode());
        room.setName(request.getName());
        room.setActive(request.isActive());
        return room;
    }

    public Room updateRoomFromRequest(UpdateRoomRequest request, Room existingRoom) {
        if(request.getId() == 0){
            return null;
        }else {
            existingRoom.setId(request.getId());
        }
        if (request.getCapacity() > 0) {
            existingRoom.setCapacity(request.getCapacity());
        }
        if (request.getType() != null) {
            existingRoom.setType(request.getType());
        }
        if (request.getDescription() != null) {
            existingRoom.setDescription(request.getDescription());
        }
        if (request.getCinemaId() > 0) {
            existingRoom.setCinemaId(request.getCinemaId());
        }
        if (request.getCode() != null) {
            existingRoom.setCode(request.getCode());
        }
        if (request.getName() != null) {
            existingRoom.setName(request.getName());
        }
        existingRoom.setActive(request.isActive());
        return existingRoom;
    }

    public List<RoomDTO> entityToDTOList(List<Room> roomList){
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room:roomList){
            roomDTOList.add(entityToDTO(room));
        }
        return roomDTOList;
    }
}

