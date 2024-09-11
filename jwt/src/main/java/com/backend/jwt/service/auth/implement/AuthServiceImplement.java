package com.backend.jwt.service.auth.implement;

import com.backend.jwt.config.jwt.JwtUtil;
import com.backend.jwt.dao.auth.UserMapper;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.dto.reqeust.auth.RefreshTokenRequestDto;
import com.backend.jwt.dto.reqeust.auth.TokenRequestDto;
import com.backend.jwt.dto.reqeust.auth.SignUpRequestDto;
import com.backend.jwt.dto.response.auth.TokenResponseDto;
import com.backend.jwt.dto.response.auth.SignUpResponseDto;
import com.backend.jwt.entity.member.Member;
import com.backend.jwt.entity.member.MemberRole;
import com.backend.jwt.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImplement implements AuthService {

    private final UserMapper dao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원 가입
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {
            // email check
            String email = dto.getEmail();
            boolean existedEmail = dao.existsByEmail(email);
            if (existedEmail) return SignUpResponseDto.duplicateEmail();

            // username check
            String username = dto.getUsername();
            boolean existedUsername = dao.existsByUsername(username);
            if (existedUsername) return SignUpResponseDto.duplicateUsername();
            // telnumber check
            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = dao.existsByTelNumber(telNumber);
            if (existedTelNumber) return SignUpResponseDto.duplicateTelNumber();


            if(dto.getRole() == null){
                dto.setRole(String.valueOf(MemberRole.ROLE_USER));
            }
            // password 암호화

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);


            // 저장
            Member member = new Member(dto);
            dao.signUp(member);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }



    // 토큰 발급.
    @Override
    public ResponseEntity<? super TokenResponseDto> token(TokenRequestDto dto) {
        String access  = null;
        String refresh = null;
        Integer userId = null;
        String username = null;
        try {
            // email로 유저정보 가져와서 검증
            String email = dto.getEmail();
            Member member = dao.findByEmail(email);
            if(member == null) return TokenResponseDto.loginFailed();
            String role  = member.getRole().toString();
            // 비번 검증
            String password = dto.getPassword();
            String encodedPassword = member.getPassword();

            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return TokenResponseDto.loginFailed();
            // 위의 과정이 성공하였으면, 토큰 생성

            userId = member.getUid();
            username = member.getUsername();
            access = jwtUtil.createAccessToken(email,role,userId);
            refresh = jwtUtil.createRefreshToken(email,role,userId);

            boolean existsByRefreshToken = dao.existsByRefreshToken(userId);
            if(existsByRefreshToken) {
                Integer deleteRefreshToken = dao.deleteRefreshToken(userId);
                log.info("delete refresh token success : {}", deleteRefreshToken) ;
            }


            // token 발급 받으면 refresh token은 DB에 insert
            Integer refreshToken = dao.insertRefreshToken(userId,refresh);
            log.info("refreshToken: " + refreshToken);


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return TokenResponseDto.success(access,refresh,userId,username);
    }

    // refreshToken 재발급
    @Override
    public ResponseEntity<? super TokenResponseDto> refreshToken(RefreshTokenRequestDto dto) {
        String access  = null;
        String refresh = null;
        Integer userId = null;
        String username = null;

        try {

            // 1. refresh token 만료시간 검증.
            boolean isExpired = jwtUtil.isExpiredRefresh(dto.getRefreshToken());
            if(isExpired){
                return TokenResponseDto.tokenExpired();
            }

            // 2. token에서 userId 빼오기
            // 이유는 한 계정당 하나의 고유한 refreshToken만 가질수 있다.
            userId = jwtUtil.getUserIdRefresh(dto.getRefreshToken());

            log.info("userId {} ",userId);
            // 3. DB에 refresh token이 있는지 검증.
            boolean isExistDB = dao.existsByRefreshToken(userId);
            log.info("DB exists {}",isExistDB);
            if(!isExistDB){
                return TokenResponseDto.invalidToken();
            }else {
                // 있다면, 삭제...?
                Integer deleteRefreshToken = dao.deleteRefreshToken(userId);
                log.info("delete refresh token success : {}", deleteRefreshToken) ;

                // 4. 있다면, refreshToken에서 email 가져오기.
                String email = jwtUtil.getEmailRefresh(dto.getRefreshToken());
                Member member = dao.findByEmail(email);
                if(member == null) return TokenResponseDto.loginFailed();
                String role  = member.getRole().toString();

                userId = member.getUid();
                username = member.getUsername();
                access = jwtUtil.createAccessToken(email,role,userId);
                refresh = jwtUtil.createRefreshToken(email,role,userId);


                // token 발급 받으면 refresh token은 DB에 insert
                Integer refreshToken = dao.insertRefreshToken(userId,refresh);
                log.info("refreshToken: " + refreshToken);
            }


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }


        return TokenResponseDto.success(access,refresh,userId,username);
    }

    // 로그아웃
    @Override
    public ResponseEntity<? super TokenResponseDto> logOut(RefreshTokenRequestDto dto) {

        try {
            // 1. token에서 유저정보 뺴오기.
            Integer userId = jwtUtil.getUserIdRefresh(dto.getRefreshToken());
            // 2. DB에 존재하는지 확인.
            if(!dao.existsByRefreshToken(userId)){
                return TokenResponseDto.invalidToken();
            }
            // 3. Refresh Token 삭제
            Integer deleteRefreshToken = dao.deleteRefreshToken(userId);
            log.info("delete refresh token success : {}", deleteRefreshToken);


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return TokenResponseDto.success();
    }
}
