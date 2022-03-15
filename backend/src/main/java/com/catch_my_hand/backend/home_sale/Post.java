package com.catch_my_hand.backend.home_sale;


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
    private LocalDateTime time;
    private String content;
    
    // 성공
    public Post(){}

    public Post(Integer id, String imageuri, String title, String sell, LocalDateTime time, String content) {
        this.id = id;
        this.imageuri = imageuri;
        this.title = title;
        this.sell = sell;
        this.time = time;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}