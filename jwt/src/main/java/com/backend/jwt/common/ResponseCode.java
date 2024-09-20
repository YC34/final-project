package com.backend.jwt.common;

public interface ResponseCode {

    // java8 version 부터  public static final을 생략해도 된다.
    // 성공 Http Status 200
    String SUCCESS = "SU";


    // Http Status 400
    // 유효성 검증 실패
    String VALIDATE_FAILED = "VF";
    // 중북된 이메일
    String DUPLICATE_EMAIL = "DE";
    // 중복된 유저명 or 별명
    String DUPLICATE_USERNAME = "DU";
    // 중복된 핸드폰 번호
    String DUPLICATE_TEL_NUMBER = "DT";
    // 존재하지 않는 유저
    String NOT_EXISTED_USER ="NE";
    // 존재하지 않는 게시물
    String NOT_EXISTED_BOARD ="NB";

    // 존재하지 않는 코드
    String NOT_EXISTED_CODE = "NEC";
    // 존재하지 않는 type
    String NOT_EXISTED_TYPE = "NET";

    // token 만료.
    String TOKEN_EXPIRED ="TE";
    // 존재하지 않는 토큰
    String TOKEN_NOT_EXISTED = "NT";


    // Http Status 401
    // 로그인 실패
    String LOGIN_IN_FAIL = "LF";
    // 인증 실패
    String AUTHENTICATION_FAIL = "AF";

    // Http Status 403
    // 권한 없음
    String NO_PERMISSION = "NP";


    // Http Status 500
    // DB error
    String DATABASE_ERROR ="DBE";





}
