package com.catch_my_hand.backend.homesiteTest;

import com.catch_my_hand.backend.SlackmessageService;
import org.junit.jupiter.api.Test;

public class SlackmessageServiceTest {

    @Test
    void 메시지전송() {
        SlackmessageService.send("Salck 테스트진행중");
    }

}
