package com.example.server.controller;

import com.example.server.response.BaseResponse;
import com.example.server.response.CarListResponse;
import com.example.server.response.DriverListResponse;
import com.example.server.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
