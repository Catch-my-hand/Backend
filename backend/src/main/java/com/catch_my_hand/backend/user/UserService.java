package com.catch_my_hand.backend.user;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.config.BaseResponseStatus;
import com.catch_my_hand.backend.home_sale.Home_saleDao;
import com.catch_my_hand.backend.user.model.PostUserReq;
import com.catch_my_hand.backend.user.model.PostUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final Home_saleDao home_saleDao;

    public UserService(UserDao userDao, UserProvider userProvider, Home_saleDao home_saleDao) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.home_saleDao = home_saleDao;
    }

    // 회원가입
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        if (userProvider.check_login_id(postUserReq.getPhoneNum()) == 1) {
            throw new BaseException(BaseResponseStatus.POST_USERS_EXISTS_PHONENUM);
        }
        try {
            int useridx = userDao.createUser(postUserReq);
            return new PostUserRes(useridx);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    // 유저 삭제
    public int withdraw(int useridx) throws BaseException {
        try {
            int withdrawUserCnt = userDao.deleteUser(useridx);
            home_saleDao.withdrawPet(useridx);
            return withdrawUserCnt;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.USERS_STATUS_NOT_ACTIVE);
        }
    }
}
