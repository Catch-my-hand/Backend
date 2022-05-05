package com.catch_my_hand.backend.Home_sale.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
