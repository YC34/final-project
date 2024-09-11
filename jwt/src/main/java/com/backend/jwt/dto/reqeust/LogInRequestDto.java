package com.backend.jwt.dto.reqeust;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogInRequestDto {
    private String accessToken;
    private String refreshToken;
}
