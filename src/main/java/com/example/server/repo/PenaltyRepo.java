package com.example.server.repo;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.PenaltyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PenaltyRepo extends CrudRepository<PenaltyEntity, Long> {
}
