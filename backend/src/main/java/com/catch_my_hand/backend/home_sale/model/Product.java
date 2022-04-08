package com.catch_my_hand.backend.home_sale.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private int productidx;
    private int useridx;
    private int categoryidx;
    private String title;
    private int price;
    private String content;
    private String status;
    private int buyer;
}
