package com.example.server.entity;

import lombok.Data;

@Data
public class DriverEntity {

    private long driver_Id;
    private String fullName;
    private String passportSeries;
    private String passportNumber;
    private String birthday;
}
