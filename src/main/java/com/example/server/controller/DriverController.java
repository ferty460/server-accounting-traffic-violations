package com.example.server.controller;

import com.example.server.entity.DriverEntity;
import com.example.server.response.*;
import com.example.server.service.DriverService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/v1/driver")
public class DriverController {

    private DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    // все водители
    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new DriverListResponse(service.getAll()));
    }

    // добавление
    @PostMapping("/add")
    public ResponseEntity<BaseResponse> save(@Valid @RequestBody DriverEntity data) {
        try {
            DriverEntity temp = service.save(data);
            return ResponseEntity.ok(new DriverResponse(true, "Водитель добавлен", temp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DriverResponse(false, e.getMessage(), null));
        }
    }

    // удаление по id
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> delete(@RequestParam("id") DriverEntity data) {
        try {
            service.delete(data.getDriver_Id());
            return ResponseEntity.ok(new BaseResponse(true, "Водитель удален"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // редактирование
    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody DriverEntity data) {
        try {
            service.save(data);
            return ResponseEntity.ok(new DriverResponse(true, "В данные об автомобиле внесены изменения", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DriverResponse(false, e.getMessage(), null));
        }
    }

    // 8. поиск по данным паспорта
    @GetMapping("/passport")
    public ResponseEntity<BaseResponse> getByPassport(@RequestParam("series") String series, @RequestParam("number") String number) {
        try {
            DriverEntity data = service.getByPassport(series, number);
            return ResponseEntity.ok(new DriverResponse(true, "Водитель", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // 7. поиск по номеру авто
    @GetMapping("/car")
    public ResponseEntity<BaseResponse> getByCarNumber(@RequestParam("number") String number) {
        try {
            DriverEntity data = service.getByCarNumber(number);
            return ResponseEntity.ok(new DriverResponse(true, "Водитель", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // 6. поиск всех по дате нарушения
    @GetMapping("/violation")
    public ResponseEntity<BaseResponse> getAllByViolationTime(@RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date time) {
        return ResponseEntity.ok(new DriverListResponse(service.getAllByViolationTime(time)));
    }

    // 5. сумма штрафов больше ук. значения
    @GetMapping("/penalties_sum")
    public ResponseEntity<BaseResponse> getAllByViolationSumGreater(@RequestParam("n") int n) {
        return ResponseEntity.ok(new DriverListResponse(service.getAllByViolationSumGreater(n)));
    }

    // 4. больше одного нарушения
    @GetMapping("/violations_greater1")
    public ResponseEntity<BaseResponse> getAllByViolationsGreaterOne() {
        return ResponseEntity.ok(new DriverListResponse(service.getAllByViolationsCountGreaterOne()));
    }

    // 3. список водителей с определенным нарушением
    @GetMapping("/violations")
    public ResponseEntity<BaseResponse> getAllByViolationKind(@RequestParam("kind") String kind) {
        return ResponseEntity.ok(new DriverListResponse(service.getAllByViolationKind(kind)));
    }

    // 2. список водителей, оплативших часть штрафа
    @GetMapping("/paid_lessPenalty")
    public ResponseEntity<BaseResponse> getAllByPaidLessPenalty() {
        return ResponseEntity.ok(new DriverListResponse(service.findByViolationsPaidNotFully()));
    }

    // 1. список водителей, не оплативших штраф
    @GetMapping("/paid_equal0")
    public ResponseEntity<BaseResponse> getAllByPaidEqual0() {
        return ResponseEntity.ok(new DriverListResponse(service.getAllByPaidEqual0()));
    }
}
