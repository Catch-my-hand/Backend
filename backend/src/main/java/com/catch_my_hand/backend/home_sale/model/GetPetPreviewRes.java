package com.catch_my_hand.backend.home_sale.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetPetPreviewRes {
    private int productidx;
    private String productImgUrl;
    private String title;
    private String price;
    private String status;
}
