package com.backend.jwt.dto.reqeust.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
