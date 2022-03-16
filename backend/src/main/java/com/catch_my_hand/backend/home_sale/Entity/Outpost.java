package com.catch_my_hand.backend.home_sale.Entity;

import javax.persistence.*;

@Entity
@Table(name = "outpostTBL")
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

}
