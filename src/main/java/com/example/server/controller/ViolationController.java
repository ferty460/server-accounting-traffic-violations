package com.example.server.controller;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.PenaltyEntity;
import com.example.server.entity.ViolationEntity;
import com.example.server.response.*;
import com.example.server.service.ViolationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/violation")
public class ViolationController {

    private ViolationService service;

    public ViolationController(ViolationService service) {
        this.service = service;
    }

    // все водители
    @GetMapping("/all")
    public ResponseEntity<BaseResponse> all() {
        return ResponseEntity.ok(new ViolationListResponse(service.getAll()));
    }

    // добавление
    @PostMapping("/add")
    public ResponseEntity<BaseResponse> save(@Valid @RequestBody ViolationEntity data) {
        try {
            ViolationEntity temp = service.save(data);
            return ResponseEntity.ok(new ViolationResponse(true, "Нарушение добавлено", temp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ViolationResponse(false, e.getMessage(), null));
        }
    }

    // удаление по id
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> delete(@RequestParam("id") ViolationEntity data) {
        try {
            service.delete(data.getViolation_Id());
            return ResponseEntity.ok(new BaseResponse(true, "Нарушение удалено"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // редактирование
    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody ViolationEntity data) {
        try {
            service.save(data);
            return ResponseEntity.ok(new ViolationResponse(true, "В данные нарушения внесены изменения", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ViolationResponse(false, e.getMessage(), null));
        }
    }

    // список нарушений владельца
    @GetMapping("/all_byDriver")
    public ResponseEntity<BaseResponse> getAllByDriver(@RequestParam("id") DriverEntity driver) {
        return ResponseEntity.ok(new ViolationListResponse(service.getAllByDriver(driver)));
    }
}
