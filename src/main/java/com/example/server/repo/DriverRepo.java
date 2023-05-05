package com.example.server.repo;

import com.example.server.entity.DriverEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DriverRepo extends CrudRepository<DriverEntity, Long> {
    DriverEntity findByPassportSeriesAndPassportNumber(String series, String number);
}
