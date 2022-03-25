package com.catch_my_hand.backend.home_sale.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "postingtbl")
public class Outpost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String sell;
    private String imageuri;

    public Outpost() {}

    public Outpost(String title, String sell, String imageuri) {

        this.title = title;
        this.sell = sell;
        this.imageuri = imageuri;
    }

}
