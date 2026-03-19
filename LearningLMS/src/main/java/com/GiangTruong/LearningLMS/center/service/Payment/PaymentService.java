package com.GiangTruong.LearningLMS.center.service.Payment;

import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentReq;
import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentRes;
import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentSummaryRes;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    PaymentRes create(PaymentReq req);

    List<PaymentRes> getByStudent(Long studentId);

    List<PaymentRes> getByStudentAndClass(Long studentId, Long classId);

    Double getTotalPaid(Long studentId, Long classId);

    PaymentSummaryRes getPaymentSummary(Long studentId, Long classId);
}
