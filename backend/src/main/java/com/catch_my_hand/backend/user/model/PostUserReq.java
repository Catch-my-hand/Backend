package com.catch_my_hand.backend.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUserReq {

    private String nickname;
    private String phoneNum;
    private String login_id;
    private String login_pw;

}
