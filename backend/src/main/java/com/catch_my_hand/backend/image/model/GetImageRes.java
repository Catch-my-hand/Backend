package com.catch_my_hand.backend.image.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetImageRes {
    private int imgidx;
    private String imgUrl;
}
