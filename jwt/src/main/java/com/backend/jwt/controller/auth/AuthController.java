package com.backend.jwt.controller.auth;


import com.backend.jwt.dto.reqeust.auth.RefreshTokenRequestDto;
import com.backend.jwt.dto.reqeust.auth.TokenRequestDto;
import com.backend.jwt.dto.reqeust.auth.SignUpRequestDto;
import com.backend.jwt.dto.response.auth.TokenResponseDto;
import com.backend.jwt.dto.response.auth.SignUpResponseDto;
import com.backend.jwt.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    // controller에는 비지니스로직을 만들면 안된다.

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(@RequestBody
                                                            @Valid SignUpRequestDto dto,  BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파라미터가 잘못되었습니다.");
        }
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(dto);
        return response;
    }


    // 토큰 발급.
    @PostMapping("/log-in")
    public ResponseEntity<? super TokenResponseDto> token(@RequestBody @Valid TokenRequestDto dto , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파라미터가 잘못되었습니다.");
        }
        ResponseEntity<? super TokenResponseDto> response = authService.token(dto);
        return response;
    }

    //refreshToken으로 토큰 재발급
    @PostMapping("/refresh-token")
    public ResponseEntity<? super TokenResponseDto> refreshToken(@RequestBody @Valid RefreshTokenRequestDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파라미터가 잘못되었습니다.");
        }

        ResponseEntity<? super TokenResponseDto> response = authService.refreshToken(dto);
        return response;
    }

    // header에 로그인 정보를 담아서보낸다. accessToken 유일하게 secuㄱty가 적용된 부분.
    @DeleteMapping("/log-out")
    public ResponseEntity<? super TokenResponseDto> logout(@RequestBody @Valid RefreshTokenRequestDto dto , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파라미터가 잘못되었습니다.");
        }

        ResponseEntity<? super TokenResponseDto> response = authService.logOut(dto);

        return response;
    }
}
