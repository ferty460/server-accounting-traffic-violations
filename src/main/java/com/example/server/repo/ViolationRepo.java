package com.example.server.repo;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.ViolationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ViolationRepo extends CrudRepository<ViolationEntity, Long> {
    // 10. список нарушений, совершенных ук. водителем
    @Query(nativeQuery = true,
            value = "SELECT v.* FROM violation v WHERE v.driver_id = :driver")
    Iterable<ViolationEntity> findAllByDriver(int driver);
}
