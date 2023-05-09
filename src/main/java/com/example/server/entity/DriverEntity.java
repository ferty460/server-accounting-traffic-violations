package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "driver")
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driver_Id;

    private String fullName;

    private String passportSeries;

    private String passportNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    @JsonIgnore
    @OneToMany(mappedBy = "driver")
    private List<CarEntity> cars;

    @JsonIgnore
    @OneToMany(mappedBy = "driver")
    private List<ViolationEntity> violations;
}
