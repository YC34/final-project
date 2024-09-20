package com.backend.jwt.dto.response.auth;

import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import com.backend.jwt.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@Getter
public class TokenResponseDto extends ResponseDto {

    private String accessToken;
    private String refreshToken;
    private String username;
    private Integer userId;

    private TokenResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.LOGOUT_SUCCESS);
    }

    private TokenResponseDto(String accessToken, String refreshToken, Integer userId, String username) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.username = username;
    }


    // 200 로그인 성공
    public static ResponseEntity<TokenResponseDto> success(String accessToken, String refreshToken, Integer userId, String username){
        TokenResponseDto result = new TokenResponseDto(accessToken,refreshToken,userId,username);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 200 로그아웃 성공
    public static ResponseEntity<TokenResponseDto> success(){
        TokenResponseDto result = new TokenResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // status 400 만료시간 지남
    public static ResponseEntity<ResponseDto> tokenExpired() {
        ResponseDto result = new ResponseDto(ResponseCode.TOKEN_EXPIRED,ResponseMessage.TOKEN_EXPIRED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    // status 400 DB에 없음.
    public static ResponseEntity<ResponseDto> invalidToken() {
        ResponseDto result = new ResponseDto(ResponseCode.TOKEN_NOT_EXISTED, ResponseMessage.TOKEN_NOT_EXISTED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    // 401
    public static ResponseEntity<ResponseDto> loginFailed(){
        ResponseDto result = new ResponseDto(ResponseCode.LOGIN_IN_FAIL, ResponseMessage.LOGIN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }



}
