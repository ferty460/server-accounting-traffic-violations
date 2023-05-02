package com.example.server.service;

import com.example.server.entity.CarEntity;
import com.example.server.repo.CarRepo;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepo repo;
    public CarService(CarRepo repo) {
        this.repo = repo;
    }
    public void save(CarEntity car) {
        repo.save(car);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
