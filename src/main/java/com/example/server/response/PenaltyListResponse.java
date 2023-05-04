package com.example.server.response;

import com.example.server.entity.PenaltyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PenaltyListResponse extends BaseResponse {

    private Iterable<PenaltyEntity> data;
    public PenaltyListResponse(Iterable<PenaltyEntity> data) {
        super(true, "Штрафы");
        this.data = data;
    }
}
