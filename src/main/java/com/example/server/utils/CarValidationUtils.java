package com.example.server.utils;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.exception.ValidationExceptionCar;
import com.example.server.exception.ValidationExceptionDriver;

public class CarValidationUtils {
    public static void validateCars(CarEntity car) throws ValidationExceptionCar {
        String brand = car.getBrand();
        if (brand == null || brand.isBlank()) {
            throw new ValidationExceptionCar("Поле \"Марка автомобиля\" не должно быть пустым");
        }
        String number = car.getNumber();
        if ( number == null || number.isBlank() || !(number.matches("[АВЕКМНОРСТУХ][0-9]{3}[АВЕКМНОРСТУХ]{2}"))) {
            throw new ValidationExceptionCar("Поле \"Номер автомобиля\" не соответствует формату (А111АА)");
        }
    }

    public static void validateDriver(String driver) {
        if (!driver.matches("[0-9]+")) {
            throw new ValidationExceptionDriver("Не соответствует формату id");
        }
    }
}
