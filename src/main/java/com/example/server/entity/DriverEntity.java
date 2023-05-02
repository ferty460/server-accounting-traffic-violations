package com.example.server.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "driver")
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driver_Id;

    @NotBlank(message = "Поле для ФИО не может быть пустым")
    private String fullName;

    @NotBlank(message = "Поле для Серии паспорта не может быть пустым")
    private String passportSeries;

    @NotBlank(message = "Поле для Номера паспорта не может быть пустым")
    private String passportNumber;

    @NotBlank(message = "Поле для Даты рождения не может быть пустым")
    private String birthday;
}
