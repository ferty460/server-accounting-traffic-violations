package com.example.server.response;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.ViolationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ViolationListResponse extends BaseResponse {

    private Iterable<ViolationEntity> data;
    public ViolationListResponse(Iterable<ViolationEntity> data) {
        super(true, "Водители");
        this.data = data;
    }
}
