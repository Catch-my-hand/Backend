package com.catch_my_hand.backend.user;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.config.BaseResponse;
import com.catch_my_hand.backend.config.BaseResponseStatus;
import com.catch_my_hand.backend.user.model.PostLoginReq;
import com.catch_my_hand.backend.user.model.PostLoginRes;
import com.catch_my_hand.backend.user.model.PostUserReq;
import com.catch_my_hand.backend.user.model.PostUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;

    @Autowired
    private final UserService userService;

    public UserController(UserProvider userProvider, UserService userService) {
        this.userProvider = userProvider;
        this.userService = userService;
    }

    // 회원가입 : api/v1/users/sign-up
    @ResponseBody
    @PostMapping("sign-up")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        if (postUserReq.getLogin_id() == null) {
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_PHONENUM);
        }
        try {
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 로그인 : api/v1/users/log-in
    @ResponseBody
    @PostMapping("/log-in")
    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq) {
        try {
            PostLoginRes postLoginRes = userProvider.login(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}
