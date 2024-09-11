package com.backend.jwt.dto.reqeust.auth;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {


    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8,max = 20)
    private String password;
    @NotBlank
    private String username;
    @NotBlank
    @Pattern(regexp = "^[0-9]{11,13}$")
    private String telNumber;
    private String address;
    private String addressDetail;
    private String role;

}
