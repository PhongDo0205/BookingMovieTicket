package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Bill;
import com.example.BookingMovieTickets.Payload.DTO.BillDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillConverter {
    public BillDTO entityToDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(bill.getId());
        billDTO.setTotalMoney(bill.getTotalMoney());
        billDTO.setTradingCode(bill.getTradingCode());
        billDTO.setCreateTime(bill.getCreateTime());
        billDTO.setCustomerId(bill.getCustomerId());
        billDTO.setName(bill.getName());
        billDTO.setUpdateTime(bill.getUpdateTime());
        billDTO.setPromotionId(bill.getPromotionId());
        billDTO.setActive(bill.isActive());
        billDTO.setBillStatusId(bill.getBillStatusId());
        return billDTO;
    }

    public List<BillDTO> entityToDTOList(List<Bill> billList) {
        List<BillDTO> billDTOList = new ArrayList<>();
        for (Bill bill : billList) {
            assert false;
            billDTOList.add(entityToDTO(bill));
        }
        return billDTOList;
    }

//    public Bill addBillFromRequest(Bill request) {
//        Bill bill = new Bill();
//        bill.setTotalMoney(request.getTotalMoney());
//        bill.setTradingCode(request.getTradingCode());
//        bill.setCreateTime(request.getCreateTime());
//        bill.setCustomerId(request.getCustomerId());
//        bill.setCreateAt(request.getCreateAt());
//        bill.setName(request.getName());
//        bill.setUpdateTime(request.getUpdateTime());
//        bill.setPromotionId(request.getPromotionId());
//        bill.setActive(request.isActive());
//        bill.setBillStatusId(request.getBillStatusId());
//        return bill;
//    }

//    public Bill updateBillFromRequest(UpdateBillRequest request, Bill existingBill) {
//        if (request.getId() == 0) {
//            return null;
//        } else {
//            existingBill.setId(request.getId());
//        }
//
//        if (request.getTotalMoney() > 0) {
//            existingBill.setTotalMoney(request.getTotalMoney());
//        }
//
//        if (request.getTradingCode() != null) {
//            existingBill.setTradingCode(request.getTradingCode());
//        }
//
//        if (request.getCreateTime() != null) {
//            existingBill.setCreateTime(request.getCreateTime());
//        }
//
//        if (request.getCustomerId() > 0) {
//            existingBill.setCustomerId(request.getCustomerId());
//        }
//
//        if (request.getCreateAt() != null) {
//            existingBill.setCreateAt(request.getCreateAt());
//        }
//
//        if (request.getName() != null) {
//            existingBill.setName(request.getName());
//        }
//
//        if (request.getUpdateTime() != null) {
//            existingBill.setUpdateTime(request.getUpdateTime());
//        }
//
//        if (request.getPromotionId() > 0) {
//            existingBill.setPromotionId(request.getPromotionId());
//        }
//
//        existingBill.setIsActive(request.isActive());
//
//        if (request.getBillStatusId() > 0) {
//            existingBill.setBillStatusId(request.getBillStatusId());
//        }
//
//        return existingBill;
//    }
}

