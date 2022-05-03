package com.catch_my_hand.backend;

import com.catch_my_hand.backend.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.web.servlet.function.ServerResponse.status;


public class DBTest extends SpringTestSupport{

    @Test
    public void test() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mariadb://220.67.115.29:3388/ats1113", "ats1113", "dmlqls335");
        System.out.println(con);
    }

    @Test
    void 유저쓰기() throws Exception {
        String user = mapper.writeValueAsString(new User(1,"김의빈", "123124124","fdgsda","124124"));

        mockMvc.perform(
                        post("/api/v1/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(user)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
