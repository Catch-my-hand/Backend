package com.catch_my_hand.backend.Home_sale.model;

import com.catch_my_hand.backend.image.model.GetImageRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetProductRes {

    //User
    private int useridx;
    private String userimgUrl;
    private String nickname;


    //pet
    private int productidx;
    private String categoryName;
    private List<GetImageRes> productimgList;
    private String title;
    private String status;
    private String content;
    private int price;
}
