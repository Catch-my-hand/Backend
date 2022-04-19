package com.catch_my_hand.backend.user;


import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.config.BaseResponseStatus;
import com.catch_my_hand.backend.user.model.PostLoginReq;
import com.catch_my_hand.backend.user.model.PostLoginRes;
import com.catch_my_hand.backend.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.catch_my_hand.backend.config.BaseResponseStatus.*;

@Service
public class UserProvider {

    private final UserDao userDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }

    // 로그인
    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
        User user = userDao.getPwd(postLoginReq);
        String login_pw = user.getLogin_pw(); // 암호키 복호화 진행 예정

        if (postLoginReq.getLogin_pw().equals(login_pw)) {
            int useridx = userDao.getPwd(postLoginReq).getUseridx();
            return new PostLoginRes(useridx);
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

    // id 검사
    public int check_login_id(String login_id) throws BaseException {
        try {
            return userDao.check_login_id(login_id);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}

