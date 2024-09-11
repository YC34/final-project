package com.backend.jwt.entity.member;


import com.backend.jwt.dto.reqeust.auth.SignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Integer uid;
    private String email;
    private String username;
    private String password;
    private String telNumber;
    private String addressDetail;
    private String address;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    private String updateYn;
    private String deleteYn;
    private MemberRole role;


    public Member(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.username = dto.getUsername();
        this.telNumber = dto.getTelNumber();
        this.role = MemberRole.valueOf(dto.getRole().toUpperCase());
    }
}
