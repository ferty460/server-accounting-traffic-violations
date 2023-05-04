package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "penalty")
public class PenaltyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penalty_Id;

    private String kind;

    private int penalty;

    @JsonIgnore
    @OneToMany(mappedBy = "penalty")
    private List<ViolationEntity> violations;
}
