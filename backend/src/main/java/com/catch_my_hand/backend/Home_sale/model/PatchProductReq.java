package com.catch_my_hand.backend.Home_sale.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchProductReq {
    private int productidx;
    private String status;
    private int buyer;

    // 상태 변경
    public PatchProductReq(int productidx, String status) {
        this.productidx = productidx;
        this.status = status;
    }

    // 구매자 변경
    public PatchProductReq(int productidx, int buyer) {
        this.productidx = productidx;
        this.buyer = buyer;
    }
}
