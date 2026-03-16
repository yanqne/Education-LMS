package com.GiangTruong.LearningLMS.center.entity;

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

    private BigDecimal amount;

    private LocalDate paymentDate;

    private String method;

    @Column(columnDefinition = "TEXT")
    private String note;

    private LocalDateTime createdAt;
}