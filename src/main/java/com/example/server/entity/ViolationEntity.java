package com.example.server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ViolationEntity {
    private long violation_Id;
    private Date time;
    private String kind;
    private int penalty;
}
