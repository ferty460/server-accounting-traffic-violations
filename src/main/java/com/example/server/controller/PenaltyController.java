package com.example.server.controller;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.PenaltyEntity;
import com.example.server.entity.ViolationEntity;
import com.example.server.response.*;
import com.example.server.service.PenaltyService;
import com.example.server.service.ViolationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/penalty")
public class PenaltyController {

    private PenaltyService service;

    public PenaltyController(PenaltyService service) {
        this.service = service;
    }

    // все штрафы
    @GetMapping("/all")
    public ResponseEntity<BaseResponse> all() {
        try {
            return ResponseEntity.ok(new PenaltyListResponse(service.getAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // добавление
    @PostMapping("/add")
    public ResponseEntity<BaseResponse> save(@Valid @RequestBody PenaltyEntity data) {
        try {
            PenaltyEntity temp = service.save(data);
            return ResponseEntity.ok(new PenaltyResponse(true, "Штраф добавлен", temp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // удаление по id
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> delete(@RequestParam("id") String data) {
        try {
            service.delete(data);
            return ResponseEntity.ok(new BaseResponse(true, "Штраф удален"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // редактирование
    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody PenaltyEntity data) {
        try {
            service.save(data);
            return ResponseEntity.ok(new PenaltyResponse(true, "В данные штрафа внесены изменения", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new PenaltyResponse(false, e.getMessage(), null));
        }
    }
}
