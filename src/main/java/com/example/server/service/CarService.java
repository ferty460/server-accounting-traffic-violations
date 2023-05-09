package com.example.server.service;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.repo.CarRepo;
import com.example.server.utils.CarValidationUtils;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepo repo;
    public CarService(CarRepo repo) {
        this.repo = repo;
    }

    // добавление
    public CarEntity save(CarEntity car) {
        CarValidationUtils.validateCars(car); // валидация
        return repo.save(car);
    }

    // удаление по id
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // все авто
    public Iterable<CarEntity> getAll() {
        return repo.findAll();
    }

    // поиск по водителю
    public Iterable<CarEntity> getAllByDriver(DriverEntity driver) {
        return repo.findAllByDriver(driver);
    }
}
