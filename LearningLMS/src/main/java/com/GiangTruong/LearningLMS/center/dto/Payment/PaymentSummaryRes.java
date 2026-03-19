package com.GiangTruong.LearningLMS.center.dto.Payment;

import com.GiangTruong.LearningLMS.center.Enum.PaymentStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PaymentSummaryRes {

    private Long studentId;
    private Long classId;

    private Double totalPaid;
    private Double courseFee;
    private Double debt;

    private PaymentStatus status;
}
