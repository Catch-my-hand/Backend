package com.catch_my_hand.backend.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */

    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */

    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
//    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
//    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
//    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    USERS_STATUS_NOT_ACTIVE(false, 2011, "탈퇴한 회원입니다."),

    // [POST] /users
    POST_USERS_EMPTY_PHONENUM(false, 2015, "휴대폰번호를 입력해주세요."),
    POST_USERS_INVALID_PHONENUM(false, 2016, "휴대폰번호 형식을 확인해주세요."),
    POST_USERS_EXISTS_PHONENUM(false,2017,"중복된 휴대폰번호입니다."),

    // [POST] /users
    POST_USERS_EMPTY_LOGIN_ID(false, 2015, "아이디를 입력해주세요."),
    POST_USERS_INVALID_LOGIN_ID(false, 2016, "아이디 형식을 지켜주세요."),
    POST_USERS_EXISTS_LOGIN_ID(false,2017,"중복된 아이디입니다."),


    // Pet

    // [PATCH] Pets
    PATCH_PRODUCTS_INVALID_STATUS(false, 2025, "유효하지 않은 상태 값입니다."),

    // Transaction
    // [PATCH] transactions
    PATCH_TRANSACTIONS_INVALID_BUYER(false,2030,"유효하지 않은 입양자 인덱스입니다."),

    /**
     * 3000 : Response 오류
     */

    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_PHONENUM(false, 3013, "중복된 휴대폰번호입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //user
    MODIFY_FAIL_USERNAME(false,4014,"유저 닉네임 수정에 실패하였습니다."),

    //Post Pet
    PATCH_PRODUCTS_ACTIVE_STATUS(false, 4021, "분양 진행중인 아이입니다."),

    MODIFY_FAIL_PRODUCT_STATUS(false, 4024, "게시물 상태 변경에 실패하였습니다."),
    MODIFY_FAIL_BUYER(false, 4025, "입양 희망자 수정에 실패하였습니다."),
    PULL_UP_FAIL(false, 4026, "상품 끌어올리기에 실패하였습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
