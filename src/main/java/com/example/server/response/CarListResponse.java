package com.example.server.response;

import com.example.server.entity.CarEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CarListResponse extends BaseResponse {

    private Iterable<CarEntity> data;
    public CarListResponse(Iterable<CarEntity> data) {
        super(true, "Автомобили");
        this.data = data;
    }
}
