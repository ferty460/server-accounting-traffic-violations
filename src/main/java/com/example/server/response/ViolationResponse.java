package com.example.server.response;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.ViolationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ViolationResponse extends BaseResponse {

    private ViolationEntity data;
    public ViolationResponse(boolean success, String message, ViolationEntity data){
        super(success,message);
        this.data = data;
    }
    public ViolationResponse(ViolationEntity data) {
        super(true, "Driver data");
    }
}
