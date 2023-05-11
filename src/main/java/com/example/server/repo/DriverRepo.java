package com.example.server.repo;

import com.example.server.entity.DriverEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Optional;

public interface DriverRepo extends CrudRepository<DriverEntity, Long> {
    // 8. поиск по данным паспорта
    DriverEntity findByPassportSeriesAndPassportNumber(String series, String number);

    // 7. поиск по номеру авто
    DriverEntity findByCars_Number(String carNumber);

    // 6. поиск по дате нарушения
    Iterable<DriverEntity> findDistinctByViolations_Time(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date time);

    // 5. сумма штрафов больше ук. значения
    @Query(nativeQuery = true,
            value = "SELECT d.* FROM driver d WHERE ( SELECT SUM(p.penalty) FROM violation v JOIN penalty p ON v.penalty_id = p.penalty_id WHERE v.driver_id = d.driver_id ) > :n")
    Iterable<DriverEntity> getAllByViolationSumGreater(int n);

    // 4. больше одного нарушения
    @Query(nativeQuery = true,
            value = "SELECT d.* " +
                    "FROM driver d " +
                    "JOIN violation v ON d.driver_id = v.driver_id " +
                    "GROUP BY d.driver_id " +
                    "HAVING COUNT(v.violation_id) > 1")
    Iterable<DriverEntity> getAllByViolationsCountGreaterOne();

    // 3. список водителей с определенным нарушением
    Iterable<DriverEntity> findDistinctByViolations_PenaltyKind(String kind);

    // 2. список водителей, оплативших часть штрафа
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT d.*" +
                    "FROM driver d " +
                    "JOIN violation v ON d.driver_id = v.driver_id " +
                    "JOIN penalty p ON v.penalty_id = p.penalty_id " +
                    "WHERE v.paid > 0 AND v.paid < p.penalty")
    Iterable<DriverEntity> findByViolationsPaidNotFully();

    // 1. список водителей, не оплативших штраф
    Iterable<DriverEntity> findDistinctByViolations_PaidEquals(int paid);
}
