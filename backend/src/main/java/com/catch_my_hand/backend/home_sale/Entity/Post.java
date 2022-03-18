package com.catch_my_hand.backend.home_sale.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "postingtbl")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String imageuri;
    private String title;
    private String sell;
    private String time;
    private String content;

    // 성공
    public Post(){}

    public Post(String imageuri, String title, String sell, String content) {
        this.imageuri = imageuri;
        this.title = title;
        this.sell = sell;
        this.content = content;
    }


}
