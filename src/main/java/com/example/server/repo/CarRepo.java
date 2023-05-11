package com.example.server.repo;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<CarEntity, Long> {
    // 9. список авто, принадлежащих ук. водителю
    @Query(nativeQuery = true,
            value = "SELECT c.* FROM car c WHERE c.driver_id = :id")
    Iterable<CarEntity> findAllByDriver_Id(int id);
}
