package com.catch_my_hand.backend.Home_sale.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetProductPreviewRes {
    private int productidx;
    private String productimgUrl;
    private String title;
    private String status;
    private int price;

}
