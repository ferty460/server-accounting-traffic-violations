package com.example.server.controller;

import com.example.server.response.BaseResponse;
import com.example.server.response.ViolationListResponse;
import com.example.server.service.ViolationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/violation")
public class ViolationController {

    private ViolationService service;

    public ViolationController(ViolationService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new ViolationListResponse(service.getAll()));
    }
}
