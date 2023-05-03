package com.example.server.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long car_Id;

    @NotBlank(message = "Поле Марка автомобиля не может быть пустым")
    private String brand;

    @NotBlank(message = "Поле Номер автомобиля не может быть пустым")
    private String number;

    @OneToMany(mappedBy = "driver")
    private List<ViolationEntity> violations;
}
