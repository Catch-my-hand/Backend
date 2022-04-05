package com.catch_my_hand.backend.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int useridx;
    private String nickname;
    private String phoneNum;
    private String login_id;
    private String login_pw;

}
