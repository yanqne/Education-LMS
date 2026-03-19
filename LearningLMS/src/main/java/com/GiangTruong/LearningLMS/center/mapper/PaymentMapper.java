package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentReq;
import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentRes;
import com.GiangTruong.LearningLMS.center.entity.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentMapper {
    public Payment toEntity(PaymentReq req){
        Payment entity = new Payment();
        entity.setAmount(req.getAmount());
        entity.setMethod(req.getMethod());
        entity.setNote(req.getNote());
        entity.setPaymentDate(LocalDate.now());
        return entity;
    }
    public PaymentRes toResponse(Payment entity){
        PaymentRes res = new PaymentRes();
        res.setId(entity.getId());
        res.setStudentId(entity.getStudent().getId());
        res.setClassId(entity.getClassEntity().getId());
        res.setAmount(entity.getAmount());
        res.setMethod(entity.getMethod());
        res.setNote(entity.getNote());
        res.setPaymentDate(entity.getPaymentDate());
        return res;
    }
}
