package com.example.server.controller;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.response.BaseResponse;
import com.example.server.response.CarListResponse;
import com.example.server.response.CarResponse;
import com.example.server.response.DriverResponse;
import com.example.server.service.CarService;
import com.example.server.utils.CarValidationUtils;
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

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> delete(@RequestParam("id") CarEntity data) {
        try {
            service.delete(data.getCar_Id());
            return ResponseEntity.ok(new BaseResponse(true, "Автомобиль удален"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody CarEntity data) {
        try {
            service.save(data);
            return ResponseEntity.ok(new CarResponse(true, "В штраф внесены изменения", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CarResponse(false, e.getMessage(), null));
        }
    }
}
