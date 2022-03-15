package com.catch_my_hand.backend.home_sale.Entity;


import javax.persistence.*;
import java.time.LocalDateTime;

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


    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
