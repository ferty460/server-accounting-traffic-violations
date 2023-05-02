package com.example.server.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@Table(name = "violation")
public class ViolationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long violation_Id;

    @NotBlank(message = "Поле для Даты нарушения не может быть пустым")
    private Date time;

    @NotBlank(message = "Поле для Вида нарушения не может быть пустым")
    private String kind;

    @NotBlank(message = "Поле для Штрафа не может быть пустым")
    private int penalty;
}
