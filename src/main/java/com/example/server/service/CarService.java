package com.example.server.service;

import com.example.server.entity.CarEntity;
import com.example.server.repo.CarRepo;
import com.example.server.utils.CarValidationUtils;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepo repo;
    public CarService(CarRepo repo) {
        this.repo = repo;
    }
    public CarEntity save(CarEntity car) {
        CarValidationUtils.validateCars(car);
        return repo.save(car);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Iterable<CarEntity> getAll() {
        return repo.findAll();
    }
}
