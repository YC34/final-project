package com.backend.jwt.dto.reqeust.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenRequestDto {
    private String refreshToken;
}
