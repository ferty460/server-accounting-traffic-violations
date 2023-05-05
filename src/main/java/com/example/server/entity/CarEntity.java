package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String brand;

    private String number;

    @ManyToOne
    @JoinColumn(name = "driver_Id")
    private DriverEntity driver;

    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private List<ViolationEntity> violations;
}
