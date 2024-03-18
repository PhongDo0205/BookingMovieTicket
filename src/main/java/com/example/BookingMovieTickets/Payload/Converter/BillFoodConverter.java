package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.BillFood;
import com.example.BookingMovieTickets.Payload.DTO.BillFoodDTO;
import com.example.BookingMovieTickets.Payload.Request.BillFood.AddBillFoodRequest;
import com.example.BookingMovieTickets.Payload.Request.BillFood.UpdateBillFoodRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillFoodConverter {

    public BillFoodDTO entityToDTO(BillFood billFood) {
        BillFoodDTO billFoodDTO = new BillFoodDTO();
        billFoodDTO.setId(billFood.getId());
        billFoodDTO.setQuantity(billFood.getQuantity());
        billFoodDTO.setBillId(billFood.getBillId());
        billFoodDTO.setFoodId(billFood.getFoodId());
        return billFoodDTO;
    }

    public List<BillFoodDTO> entityToDTOList(List<BillFood> billFoodList){
        List<BillFoodDTO> billFoodDTOList = new ArrayList<>();
        for (BillFood billFood : billFoodList) {
            billFoodDTOList.add(entityToDTO(billFood));
        }
        return billFoodDTOList;
    }

    public BillFood addBillFoodFromRequest(AddBillFoodRequest request) {
        BillFood billFood = new BillFood();
        billFood.setQuantity(request.getQuantity());
        billFood.setBillId(request.getBillId());
        billFood.setFoodId(request.getFoodId());
        return billFood;
    }

    public BillFood updateBillFoodFromRequest(UpdateBillFoodRequest request, BillFood existingBillFood) {
        if (request.getQuantity() > 0) {
            existingBillFood.setQuantity(request.getQuantity());
        }
        if (request.getBillId() > 0) {
            existingBillFood.setBillId(request.getBillId());
        }
        if (request.getFoodId() > 0) {
            existingBillFood.setFoodId(request.getFoodId());
        }
        return existingBillFood;
    }
}

