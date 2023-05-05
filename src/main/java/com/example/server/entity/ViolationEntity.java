package com.example.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "car_Id")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "driver_Id")
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(name = "penalty_Id")
    private PenaltyEntity penalty;

    private int paid;

    private Date time;
}
