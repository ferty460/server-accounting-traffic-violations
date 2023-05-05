package com.example.server.controller;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.entity.ViolationEntity;
import com.example.server.response.*;
import com.example.server.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/driver")
public class DriverController {

    private DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new DriverListResponse(service.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> save(@Valid @RequestBody DriverEntity data) {
        try {
            DriverEntity temp = service.save(data);
            return ResponseEntity.ok(new DriverResponse(true, "Водитель добавлен", temp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DriverResponse(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> delete(@RequestParam("id") DriverEntity data) {
        try {
            service.delete(data.getDriver_Id());
            return ResponseEntity.ok(new BaseResponse(true, "Водитель удален"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody DriverEntity data) {
        try {
            service.save(data);
            return ResponseEntity.ok(new DriverResponse(true, "В данные об автомобиле внесены изменения", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DriverResponse(false, e.getMessage(), null));
        }
    }
}
