package com.catch_my_hand.backend.home_sale.model;

import com.catch_my_hand.backend.image.model.GetImageRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetPetRes {

    private int useridx;
    private String userImgUrl;
    private String nickname;

    private int productidx;
    private String categoryName;
    private List<GetImageRes> petImgList;
    private String title;
    private int price;
    private String status;
    private String content;
}
