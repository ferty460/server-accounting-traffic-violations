package com.example.server.repo;

import com.example.server.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<CarEntity, Long> {
}
