package com.catch_my_hand.backend.user;

import com.catch_my_hand.backend.user.model.PostLoginReq;
import com.catch_my_hand.backend.user.model.PostUserReq;
import com.catch_my_hand.backend.user.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //회원 가입
    public int createUser(@NotNull PostUserReq postUserReq) {
        String createUserQuery = "insert into User (nickname, phoneNum, login_id, login_pw) VALUES(?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getNickname(), postUserReq.getPhoneNum(), postUserReq.getLogin_id(), postUserReq.getLogin_pw()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select Last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    //아이디 체크
    public int check_login_id(String login_id) {
        String checklogin_id_Query = "select exists(select login_id from User where login_id = ?)";
        String checklogin_id_Params = login_id;

        return this.jdbcTemplate.queryForObject(checklogin_id_Query,
                int.class,
                checklogin_id_Params);
    }

    // 로그인
    public User getPwd(@NotNull PostLoginReq postLoginReq) {
        String getPwdQuery = "select * from User where login_id = ?";
        String getPwdParams = postLoginReq.getLogin_id();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("useridx"),
                        rs.getString("nickname"),
                        rs.getString("phoneNum"),
                        rs.getString("login_id"),
                        rs.getString("login_pw")),
                getPwdParams
                );
    }

}
