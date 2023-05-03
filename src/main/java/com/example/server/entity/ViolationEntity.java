package com.example.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "violation")
public class ViolationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long violation_Id;

    @NotBlank(message = "Поле для Даты нарушения не может быть пустым")
    private Date time;

    @NotBlank(message = "Поле для Вида нарушения не может быть пустым")
    private String kind;

    @NotBlank(message = "Поле для Штрафа не может быть пустым")
    private int penalty;

    @ManyToOne
    @JoinColumn(name = "car_Id")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "driver_Id")
    private DriverEntity driver;
}
