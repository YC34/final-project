package com.backend.jwt.common;

public interface ResponseMessage {

    // 성공 Http Status 200
    String SUCCESS = "Success";
    String LOGOUT_SUCCESS = "Logout success";


    // Http Status 400
    // 유효성 검증 실패
    String VALIDATE_FAILED = "Validation Failed";
    // 중북된 이메일
    String DUPLICATE_EMAIL = "Duplicate Email";
    // 중복된 유저명 or 별명
    String DUPLICATE_USERNAME = "Duplicate Username";
    // 중복된 핸드폰 번호
    String DUPLICATE_TEL_NUMBER = "Duplicate Tel Number";
    // 존재하지 않는 유저
    String NOT_EXISTED_USER ="This user does not exist";
    // 존재하지 않는 게시물
    String NOT_EXISTED_BOARD ="This board does not exist";
    // 존재하지 않는 code
    String NOT_EXISTED_CODE = "This code does not exist";
    // 존재하지 않는 type
    String NOT_EXISTED_TYPE = "This type does not exist";
    // 존재하지 않는 type
    String NOT_EXISTED_FLAG = "This type does not flag";

    String TOKEN_EXPIRED ="Token Expired!!!";
    String TOKEN_NOT_EXISTED ="Token Not Existed!!!";


    // Http Status 401
    // 로그인 실패
    String LOGIN_IN_FAIL = "Login information is incorrect";
    // 인증 실패
    String AUTHENTICATION_FAIL = "Authorization Failed";

    // Http Status 403
    // 권한 없음
    String NO_PERMISSION = "Do Not have Permission";


    // Http Status 500
    // DB error
    String DATABASE_ERROR ="Database Error";

}
