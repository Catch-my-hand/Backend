package com.catch_my_hand.backend.homesiteTest;

import com.catch_my_hand.backend.Message;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MessageTest {

    @Test
    void 메시지전송() {
        Message.send("hello world");
    }

}
