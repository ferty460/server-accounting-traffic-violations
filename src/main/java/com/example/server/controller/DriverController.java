package com.example.server.controller;

import com.example.server.entity.DriverEntity;
import com.example.server.response.*;
import com.example.server.service.DriverService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
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
    public ResponseEntity<BaseResponse> getByPassport(@RequestParam("series") @Pattern(regexp = "[0-9]{4}") String series, @RequestParam("number") String number) {
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
        try {
            return ResponseEntity.ok(new DriverListResponse(service.getAllByViolationTime(time)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // 5. сумма штрафов больше ук. значения
    @GetMapping("/penalties_sum")
    public ResponseEntity<BaseResponse> getAllByViolationSumGreater(@RequestParam("n") Integer n) {
        try {
            return ResponseEntity.ok(new DriverListResponse(service.getAllByViolationSumGreater(n)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // 4. больше одного нарушения
    @GetMapping("/violations_greater1")
    public ResponseEntity<BaseResponse> getAllByViolationsGreaterOne() {
        try {
            return ResponseEntity.ok(new DriverListResponse(service.getAllByViolationsCountGreaterOne()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // 3. список водителей с определенным нарушением
    @GetMapping("/violations")
    public ResponseEntity<BaseResponse> getAllByViolationKind(@RequestParam("kind") String kind) {
        try {
            return ResponseEntity.ok(new DriverListResponse(service.getAllByViolationKind(kind)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // 2. список водителей, оплативших часть штрафа
    @GetMapping("/paid_lessPenalty")
    public ResponseEntity<BaseResponse> getAllByPaidLessPenalty() {
        try {
            return ResponseEntity.ok(new DriverListResponse(service.findByViolationsPaidNotFully()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    // 1. список водителей, не оплативших штраф
    @GetMapping("/paid_equal0")
    public ResponseEntity<BaseResponse> getAllByPaidEqual0() {
        try {
            return ResponseEntity.ok(new DriverListResponse(service.getAllByPaidEqual0()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }
}
