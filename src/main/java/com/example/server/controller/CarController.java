package com.example.server.controller;

import com.example.server.entity.CarEntity;
import com.example.server.response.BaseResponse;
import com.example.server.response.CarListResponse;
import com.example.server.response.CarResponse;
import com.example.server.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/car")
public class CarController {

    private CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new CarListResponse(service.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> save(@Valid @RequestBody CarEntity data) {
        try {
            CarEntity temp = service.save(data);
            return ResponseEntity.ok(new CarResponse(true, "Автомобиль добавлен", temp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CarResponse(false, e.getMessage(), null));
        }
    }
}
