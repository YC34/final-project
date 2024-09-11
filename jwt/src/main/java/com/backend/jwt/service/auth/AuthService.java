package com.backend.jwt.service.auth;

import com.backend.jwt.dto.reqeust.auth.RefreshTokenRequestDto;
import com.backend.jwt.dto.reqeust.auth.TokenRequestDto;
import com.backend.jwt.dto.reqeust.auth.SignUpRequestDto;
import com.backend.jwt.dto.response.auth.TokenResponseDto;
import com.backend.jwt.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    // 회원 가입
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto signUpRequestDto);
    // 토큰 발급
    ResponseEntity<? super TokenResponseDto> token(TokenRequestDto tokenRequestDto);
    // accesstoken 만료시 재발급. refresh로 token재발급.
    ResponseEntity<? super TokenResponseDto> refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);

    // logout 시 token 삭제.
    ResponseEntity<? super TokenResponseDto> logOut(RefreshTokenRequestDto token);
}
