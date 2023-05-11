package com.example.server.service;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.exception.ValidationExceptionDriver;
import com.example.server.repo.CarRepo;
import com.example.server.utils.CarValidationUtils;
import com.example.server.utils.DriverValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Spliterator;

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
    public void delete(String n) {
        DriverValidationUtils.validateDelete(n);
        Long id = Long.parseLong(n);
        repo.deleteById(id);
    }

    // все авто
    public Iterable<CarEntity> getAll() {
        return repo.findAll();
    }

    // поиск по водителю
    public Iterable<CarEntity> getAllByDriver(String driver) {
        CarValidationUtils.validateDriver(driver);
        int d = Integer.parseInt(driver);
        Iterable<CarEntity> cars = repo.findAllByDriver_Id(d);
        Spliterator spliterator = cars.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("Автомобилей нет");
        } else {
            return repo.findAllByDriver_Id(d);
        }
    }
}
