package com.example.server.controller;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.response.*;
import com.example.server.service.CarService;
import com.example.server.utils.CarValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/car")
public class CarController {

    private CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    // все авто
    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        try {
            return ResponseEntity.ok(new CarListResponse(service.getAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // добавление
    @PostMapping("/add")
    public ResponseEntity<BaseResponse> save(@Valid @RequestBody CarEntity data) {
        try {
            CarEntity temp = service.save(data);
            return ResponseEntity.ok(new CarResponse(true, "Автомобиль добавлен", temp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CarResponse(false, e.getMessage(), null));
        }
    }

    // удаление по id
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> delete(@RequestParam("id") String data) {
        try {
            service.delete(data);
            return ResponseEntity.ok(new BaseResponse(true, "Автомобиль удален"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // редактирование
    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody CarEntity data) {
        try {
            service.save(data);
            return ResponseEntity.ok(new CarResponse(true, "В данные автомобиля внесены изменения", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CarResponse(false, e.getMessage(), null));
        }
    }

    // список авто, принадлежащих какому-либо водителю
    @GetMapping("/all_byDriver")
    public ResponseEntity<BaseResponse> getAllByDriver(@RequestParam("id") String driver) {
        try {
            return ResponseEntity.ok(new CarListResponse(service.getAllByDriver(driver)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }

    }
}
