package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.User;
import com.example.BookingMovieTickets.Payload.DTO.UserDTO;
import com.example.BookingMovieTickets.Payload.Request.User.CreateUserRequest;
import com.example.BookingMovieTickets.Payload.Request.User.UpdateProfileRequest;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Data
public class UserConverter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO entityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPoint(user.getPoint());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
//        userDTO.setPassword(user.getPassword());
        userDTO.setRankCustomerId(user.getRankCustomerId());
        userDTO.setUserStatusId(user.getUserStatusId());
        userDTO.setActive(user.isActive());
//        userDTO.setRoleId(user.getRoleId());
        userDTO.setRoleId(user.getRoleId());
        return userDTO;
    }

    public User createNewUser(CreateUserRequest request) {
        User user = new User();
        user.setPoint(0);
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRankCustomerId(1);
        user.setUserStatusId(1);
        user.setActive(false);
//        user.setRole(Role.USER);
        user.setRoleId(2);
        return user;
    }

    public List<UserDTO> entityToDTOList(List<User> userList){
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user:userList){
            assert false;
            userDTOList.add(entityToDTO(user));
        }
        return userDTOList;
    }


    public User updateProfile(UpdateProfileRequest request, User existingUser) {
        if (request.getEmail() != null) {
            existingUser.setEmail(request.getEmail());
        }
        if (request.getName() != null) {
            existingUser.setName(request.getName());
        }
        if (request.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(request.getPhoneNumber());
        }
        return existingUser;
    }
}

