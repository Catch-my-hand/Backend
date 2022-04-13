package com.catch_my_hand.backend.homesiteTest;

import com.catch_my_hand.backend.SlackmessageService;
import org.junit.jupiter.api.Test;

public class SlackmessageServiceTest {

    @Test
    void 메시지전송() {
        SlackmessageService.send("<각 서버별 API 주소 안내 > ");
        SlackmessageService.send("< User >  ");
        SlackmessageService.send("회원가입 : http://localhost:9000/api/v1/users/sign-up ");
        SlackmessageService.send("로그인 : http://localhost:9000/api/v1/log-in ");
        SlackmessageService.send(" < 공공기관 > ");
        SlackmessageService.send("공공기관 : http://localhost:9000/api/v1/center ");
        SlackmessageService.send(" < 가정 분양 > ");
        SlackmessageService.send("분양등록 : http://localhost:9000/api/v1/home-post ");
        SlackmessageService.send("분양수정 : http://localhost:9000/api/v1/{productidx} ");
        SlackmessageService.send("분양 게시물 전체조회 : http://localhost:9000/api/v1/home-post ");
        SlackmessageService.send("현재 분양 진행중인 게시물 조회 : http://localhost:9000/api/v1/active ");
        SlackmessageService.send("분양 상태 변경 : http://localhost:9000/api/v1/{productidx}/status ");








    }

}
