package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Food;
import com.example.BookingMovieTickets.Payload.DTO.FoodDTO;
import com.example.BookingMovieTickets.Payload.Request.Food.AddFoodRequest;
import com.example.BookingMovieTickets.Payload.Request.Food.UpdateFoodRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class FoodConverter {
    public FoodDTO entityToDTO(Food food) {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setPrice(food.getPrice());
        foodDTO.setDescription(food.getDescription());
        foodDTO.setImage(food.getImage());
        foodDTO.setNameOfFood(food.getNameOfFood());
        foodDTO.setActive(food.isActive());
        return foodDTO;
    }

    public Food addFoodFromRequest(AddFoodRequest request) {
        Food food = new Food();
        food.setPrice(request.getPrice());
        food.setDescription(request.getDescription());
        food.setImage(request.getImage());
        food.setNameOfFood(request.getNameOfFood());
        food.setActive(request.isActive());
        return food;
    }

    public Food updateFoodFromRequest(UpdateFoodRequest request, Food existingFood) {
        if(request.getId() == 0){
            return null;
        }else{
            existingFood.setId(request.getId());
        }
        if (request.getPrice() > 0) {
            existingFood.setPrice(request.getPrice());
        }
        if (request.getDescription() != null) {
            existingFood.setDescription(request.getDescription());
        }
        if (request.getImage() != null) {
            existingFood.setImage(request.getImage());
        }
        if (request.getNameOfFood() != null) {
            existingFood.setNameOfFood(request.getNameOfFood());
        }
        existingFood.setActive(request.isActive());
        return existingFood;
    }

    public List<FoodDTO> entityToDTOList(List<Food> foodList){
        List<FoodDTO> foodDTOList = new ArrayList<>();
        for (Food food:foodList){
            foodDTOList.add(entityToDTO(food));
        }
        return foodDTOList;
    }
}

