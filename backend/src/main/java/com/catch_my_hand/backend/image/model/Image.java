package com.catch_my_hand.backend.image.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Image {
    private int imgidx;
    private String imgCategory;
    private String imgUrl;
    private int productidx;
    private int postidx;
    private int useridx;

}
