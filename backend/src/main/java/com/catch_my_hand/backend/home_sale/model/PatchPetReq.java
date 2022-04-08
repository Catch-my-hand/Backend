package com.catch_my_hand.backend.home_sale.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchPetReq {
    private int productidx;
    private String status;
    private int buyer;

    // 분양 상태 변경
    public PatchPetReq(int productidx, String status) {
        this.productidx = productidx;
        this.status = status;
    }

    // 분양 희망자 변경
    public PatchPetReq(int productidx, int buyer) {
        this.productidx = productidx;
        this.buyer = buyer;
    }
}
