package com.example.server.repo;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<CarEntity, Long> {
    // 9. список авто, принадлежащих ук. водителю
    Iterable<CarEntity> findAllByDriver(DriverEntity driver);
}
