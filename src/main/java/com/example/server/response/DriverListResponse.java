package com.example.server.response;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DriverListResponse extends BaseResponse {

    private Iterable<DriverEntity> data;
    public DriverListResponse(Iterable<DriverEntity> data) {
        super(true, "Водители");
        this.data = data;
    }
}
