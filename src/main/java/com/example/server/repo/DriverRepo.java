package com.example.server.repo;

import com.example.server.entity.DriverEntity;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepo extends CrudRepository<DriverEntity, Long> {
}
