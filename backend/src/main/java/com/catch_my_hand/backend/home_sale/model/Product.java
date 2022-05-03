package com.catch_my_hand.backend.home_sale.model;

import com.catch_my_hand.backend.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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
