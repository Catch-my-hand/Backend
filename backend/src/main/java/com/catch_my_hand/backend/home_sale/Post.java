package com.catch_my_hand.backend.home_sale;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PostTable")
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String ImageUri;

    private String Title;

    private Integer Sell;

    private LocalDateTime Time;

    private String Content;

    public Post() {
    }
    public Post(String ImageUri, String Title, LocalDateTime Time, String Content, Integer Sell) {
        this.ImageUri = ImageUri;
        this.Title = Title;
        this.Content = Content;
        this.Sell = Sell;
        this.Time = Time;

    }
}
