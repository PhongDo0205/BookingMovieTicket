package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Promotion;
import com.example.BookingMovieTickets.Payload.DTO.PromotionDTO;
import com.example.BookingMovieTickets.Payload.Request.Promotion.AddPromotionRequest;
import com.example.BookingMovieTickets.Payload.Request.Promotion.UpdatePromotionRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PromotionConverter {

    public PromotionDTO entityToDTO(Promotion promotion) {
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setId(promotion.getId());
        promotionDTO.setPercent(promotion.getPercent());
        promotionDTO.setQuantity(promotion.getQuantity());
        promotionDTO.setType(promotion.getType());
        promotionDTO.setEndTime(promotion.getEndTime());
        promotionDTO.setStartTime(promotion.getStartTime());
        promotionDTO.setDescription(promotion.getDescription());
        promotionDTO.setName(promotion.getName());
        promotionDTO.setActive(promotion.isActive());
        promotionDTO.setRankCustomerId(promotion.getRankCustomerId());
        return promotionDTO;
    }

    public List<PromotionDTO> entityToDTOList(List<Promotion> promotionList) {
        List<PromotionDTO> promotionDTOList = new ArrayList<>();
        for (Promotion promotion : promotionList) {
            promotionDTOList.add(entityToDTO(promotion));
        }
        return promotionDTOList;
    }

    public Promotion addPromotionFromRequest(AddPromotionRequest request) {
        Promotion promotion = new Promotion();
        promotion.setPercent(request.getPercent());
        promotion.setQuantity(request.getQuantity());
        promotion.setType(request.getType());
        promotion.setEndTime(request.getEndTime());
        promotion.setStartTime(request.getStartTime());
        promotion.setDescription(request.getDescription());
        promotion.setName(request.getName());
        promotion.setActive(true);
        promotion.setRankCustomerId(request.getRankCustomerId());
        return promotion;
    }

    public Promotion updatePromotionFromRequest(UpdatePromotionRequest request, Promotion existingPromotion) {
        if (request.getId() == 0) {
            return null;
        } else {
            existingPromotion.setId(request.getId());
        }
        if (request.getPercent() > 0) {
            existingPromotion.setPercent(request.getPercent());
        }
        if (request.getQuantity() > 0) {
            existingPromotion.setQuantity(request.getQuantity());
        }
        if (request.getType() != null) {
            existingPromotion.setType(request.getType());
        }
        if (request.getEndTime() != null) {
            existingPromotion.setEndTime(request.getEndTime());
        }
        if (request.getStartTime() != null) {
            existingPromotion.setStartTime(request.getStartTime());
        }
        if (request.getDescription() != null) {
            existingPromotion.setDescription(request.getDescription());
        }
        if (request.getName() != null) {
            existingPromotion.setName(request.getName());
        }
        existingPromotion.setActive(request.isActive());
        if (request.getRankCustomerId() > 0) {
            existingPromotion.setRankCustomerId(request.getRankCustomerId());
        }
        return existingPromotion;
    }
}

