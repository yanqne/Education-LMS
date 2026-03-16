package com.GiangTruong.LearningLMS.center.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String phone;

    private String email;

    private String specialty;

    private BigDecimal salary;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
