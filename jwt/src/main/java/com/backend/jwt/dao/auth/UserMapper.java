package com.backend.jwt.dao.auth;


import com.backend.jwt.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByTelNumber(String telNumber);
    Integer signUp(Member member);
    Member findByEmail(String email);

    Integer insertRefreshToken(Integer userId, String refresh);
    boolean existsByRefreshToken(Integer userId);
    Integer deleteRefreshToken(Integer userId);
}
