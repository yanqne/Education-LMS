package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentReq;
import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentRes;
import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentSummaryRes;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Payment.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "payment API", description = "APIs for managing payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ApiResponse<PaymentRes> create(@RequestBody PaymentReq req) {
        return new ApiResponse<>(
                true,
                "Payment created successfully",
                paymentService.create(req)
        );
    }

    @GetMapping("/student/{studentId}")
    public ApiResponse<List<PaymentRes>> getByStudent(@PathVariable Long studentId) {
        return new ApiResponse<>(
                true,
                "Payments retrieved successfully",
                paymentService.getByStudent(studentId)
        );
    }

    @GetMapping("/student/{studentId}/class/{classId}")
    public ApiResponse<List<PaymentRes>> getByStudentAndClass(
            @PathVariable Long studentId,
            @PathVariable Long classId
    ) {
        return new ApiResponse<>(
                true,
                "Payments retrieved successfully",
                paymentService.getByStudentAndClass(studentId, classId)
        );
    }

    @GetMapping("/total")
    public ApiResponse<Double> getTotalPaid(
            @RequestParam Long studentId,
            @RequestParam Long classId
    ) {
        return new ApiResponse<>(
                true,
                "Total payment calculated",
                paymentService.getTotalPaid(studentId, classId)
        );
    }
    @GetMapping("/summary")
    public ApiResponse<PaymentSummaryRes> getSummary(
            @RequestParam Long studentId,
            @RequestParam Long classId
    ) {
        return new ApiResponse<>(
                true,
                "Payment summary retrieved successfully",
                paymentService.getPaymentSummary(studentId, classId)
        );
    }
}