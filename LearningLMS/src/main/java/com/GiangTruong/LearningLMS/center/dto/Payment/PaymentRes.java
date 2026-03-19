package com.GiangTruong.LearningLMS.center.dto.Payment;

import com.GiangTruong.LearningLMS.center.Enum.Method;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Getter
@Setter
public class PaymentRes {
    private Long id;
    private Long studentId;
    private Long classId;
    private Double amount;
    private LocalDate paymentDate;
    private Method method;
    private String note;
}
