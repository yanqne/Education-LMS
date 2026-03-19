package com.GiangTruong.LearningLMS.center.entity;

import com.GiangTruong.LearningLMS.center.Enum.Method;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    private Double amount;

    private LocalDate paymentDate;

    private Method method;

    @Column(columnDefinition = "TEXT")
    private String note;

    private LocalDateTime createdAt;
}