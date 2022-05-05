package com.catch_my_hand.backend.user;

import com.catch_my_hand.backend.Home_sale.ProductDao;
import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.config.BaseResponseStatus;
import com.catch_my_hand.backend.user.model.PostUserReq;
import com.catch_my_hand.backend.user.model.PostUserRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final ProductDao productDao;


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
            productDao.withdrawProduct(useridx);
            return withdrawUserCnt;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.USERS_STATUS_NOT_ACTIVE);
        }
    }
}
